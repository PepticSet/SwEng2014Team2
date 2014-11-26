package ee.ut.math.tvt.salessystem.ui.model;

import java.text.DecimalFormat;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;

public class DetailedHistoryTableModel extends SalesSystemTableModel<SoldItem> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DetailedHistoryTableModel() {
		super(new String[] { "Id", "Name", "Price", "Quantity", "Sum" });
	}

	@Override
	protected Object getColumnValue(SoldItem item, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return item.getId();
		case 1:
			return item.getName();
		case 2:
			return (new DecimalFormat("0.00")).format(item.getPrice());
		case 3:
			return item.getQuantity();
		case 4:
			return (new DecimalFormat("0.00")).format(item.getSum());
		}
		throw new IllegalArgumentException("Column index unsuitable");
	}
	
	//add to table and database
	public void addItem(final SoldItem soldItem) {
		Session sess = HibernateUtil.currentSession();
		Transaction transaction = sess.beginTransaction();
		
		rows.add(soldItem);
		
		sess.save(soldItem);
		transaction.commit();
		
		fireTableDataChanged();
	}
}
