package ee.ut.math.tvt.salessystem.ui.model;

import java.text.DecimalFormat;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;

/**
 * Stock item table model.
 */
public class StockTableModel extends SalesSystemTableModel<StockItem> {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(StockTableModel.class);

	public StockTableModel() {
		super(new String[] { "Id", "Name", "Price", "Quantity" });
	}

	@Override
	protected Object getColumnValue(StockItem item, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return item.getId();
		case 1:
			return item.getName();
		case 2:
			return (new DecimalFormat("0.00")).format(item.getPrice());
		case 3:
			return item.getQuantity();
		}
		throw new IllegalArgumentException("Column index out of range");
	}

	/**
	 * Add new stock item to table. If there already is a stock item with same
	 * id, then existing item's quantity will be increased.
	 * 
	 * @param stockItem
	 */
	public void addItem(final StockItem stockItem) {
		Session sess = HibernateUtil.currentSession();
		Transaction transaction = sess.beginTransaction();
		try {
			StockItem item = getItemById(stockItem.getId());
			item.setQuantity(item.getQuantity() + stockItem.getQuantity());
			log.debug("Found existing item " + stockItem.getName()
					+ " increased quantity by " + stockItem.getQuantity());
			sess.save(item);
		} catch (NoSuchElementException e) {

			rows.add(stockItem);
			log.debug("Added " + stockItem.getName() + " quantity of "
					+ stockItem.getQuantity());
			sess.save(stockItem);
		}
		transaction.commit();
		fireTableDataChanged();
	}

	public void removeItem(final SoldItem soldItem) {
		try {
			StockItem item = getItemById(soldItem.getId());
			item.setQuantity(item.getQuantity() - soldItem.getQuantity());
			Session sess = HibernateUtil.currentSession();
			Transaction transaction = sess.beginTransaction();
			transaction.commit();
			fireTableDataChanged();
		} catch (NoSuchElementException e) {
		}
	}

	@Override
	public String toString() {
		final StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < headers.length; i++)
			buffer.append(headers[i] + "\t");
		buffer.append("\n");

		for (final StockItem stockItem : rows) {
			buffer.append(stockItem.getId() + "\t");
			buffer.append(stockItem.getName() + "\t");
			buffer.append(stockItem.getPrice() + "\t");
			buffer.append(stockItem.getQuantity() + "\t");
			buffer.append("\n");
		}

		return buffer.toString();
	}
}
