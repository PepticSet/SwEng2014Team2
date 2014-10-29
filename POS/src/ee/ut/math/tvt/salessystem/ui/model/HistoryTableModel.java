package ee.ut.math.tvt.salessystem.ui.model;

import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;


public class HistoryTableModel extends SalesSystemTableModel<HistoryItem> {

	public HistoryTableModel() {
		super(new String[] {"Date", "Time", "Total Price"});
	}

	@Override
	protected Object getColumnValue(HistoryItem item, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
