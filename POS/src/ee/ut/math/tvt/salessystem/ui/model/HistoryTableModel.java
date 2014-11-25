package ee.ut.math.tvt.salessystem.ui.model;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

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
			return (new SimpleDateFormat("dd.MM.yyyy")).format(item.getSaleTime());
		case 1:
			return (new SimpleDateFormat("HH:mm:ss")).format(item.getSaleTime());
		case 2:
			return (new DecimalFormat("0.00")).format(item.getSum());
		}
		throw new IllegalArgumentException("Column index unsuitable");
	}

	public void addItem(final HistoryItem HistoryItem) {
		Session sess = HibernateUtil.currentSession();
		Transaction transaction = sess.beginTransaction();
		
		rows.add(HistoryItem);
		
		sess.save(HistoryItem);
		transaction.commit();
		
		fireTableDataChanged();
	}
}
