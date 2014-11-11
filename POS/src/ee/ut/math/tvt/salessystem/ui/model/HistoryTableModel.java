package ee.ut.math.tvt.salessystem.ui.model;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;

public class HistoryTableModel extends SalesSystemTableModel<HistoryItem> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HistoryTableModel() {
		super(new String[] { "Date", "Time", "Total Price" });
	}

	@Override
	protected Object getColumnValue(HistoryItem item, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return item.getDate();
		case 1:
			return item.getTime();
		case 2:
			return item.getPrice();
		}
		throw new IllegalArgumentException("Column index unsuitable");
	}

	public void addItem(final HistoryItem item) {
		Session sess = HibernateUtil.currentSession();
		Transaction transaction = sess.beginTransaction();
		
		rows.add(item);
		
		sess.save(item);
		transaction.commit();
		
		fireTableDataChanged();
	}
}
