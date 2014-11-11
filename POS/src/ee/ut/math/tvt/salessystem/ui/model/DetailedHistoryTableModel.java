package ee.ut.math.tvt.salessystem.ui.model;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;

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
			return item.getPrice();
		case 3:
			return item.getQuantity();
		case 4:
			return item.getSum();
		}
		throw new IllegalArgumentException("Column index unsuitable");
	}

	public void addItem(final SoldItem item) {
		rows.add(item);
		fireTableDataChanged();
	}
}
