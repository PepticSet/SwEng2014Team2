package ee.ut.math.tvt.salessystem.ui.model;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.exception.OutOfStockException;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;

/**
 * Purchase history details model.
 */
public class PurchaseInfoTableModel extends SalesSystemTableModel<SoldItem> {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger
			.getLogger(PurchaseInfoTableModel.class);

	private StockTableModel warehouseTableModel;

	public PurchaseInfoTableModel(StockTableModel warehouseTableModel) {
		super(new String[] { "Id", "Name", "Price", "Quantity", "Sum" });
		this.warehouseTableModel = warehouseTableModel;
	}

	@Override
	protected Object getColumnValue(SoldItem item, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return item.getId();
		case 1:
			return item.getName();
		case 2:
			return item.getPrice();
		case 3:
			return item.getQuantity();
		case 4:
			return item.getQuantity() * item.getPrice();
		}
		throw new IllegalArgumentException("Column index out of range");
	}

	@Override
	public String toString() {
		final StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < headers.length; i++)
			buffer.append(headers[i] + "\t");
		buffer.append("\n");

		for (final SoldItem item : rows) {
			buffer.append(item.getId() + "\t");
			buffer.append(item.getName() + "\t");
			buffer.append(item.getPrice() + "\t");
			buffer.append(item.getQuantity() + "\t");
			buffer.append(item.getSum() + "\t");
			buffer.append("\n");
		}

		return buffer.toString();
	}

	/**
	 * Add new StockItem to table.
	 */
	public void addItem(final SoldItem item) throws OutOfStockException {
		Session sess = HibernateUtil.currentSession();
		Transaction transaction = sess.beginTransaction();
		
		SoldItem existingItem = null;
		for (SoldItem cartItem : rows) {
			if (cartItem.getId() == item.getId()) {
				existingItem = cartItem;
				break;
			}
		}
		int quantity = item.getQuantity();
		if (existingItem != null) {
			quantity += existingItem.getQuantity();
		}

		StockItem warehouseItem = warehouseTableModel.getItemById(item.getId());
		if (quantity > warehouseItem.getQuantity()) {
			throw new OutOfStockException();
		}

		if (existingItem != null) {
			existingItem.setQuantity(quantity);
		} else {
			rows.add(item);
		}

		log.debug("Added " + item.getName() + " quantity of "
				+ item.getQuantity());
		transaction.commit();
		fireTableDataChanged();
	}

}
