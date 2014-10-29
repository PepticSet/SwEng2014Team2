package ee.ut.math.tvt.salessystem.domain.data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HistoryItem implements Cloneable, DisplayableItem {

	
	private Long id;
	private double price;
	private String date;
	private String time;
	//private List<SoldItem> soldGoods;
	
	public HistoryItem(List<SoldItem> soldGoods) {
		super();
		//this.soldGoods = soldGoods;
		
		this.price = getSum(soldGoods);
		
		SimpleDateFormat dateF = new SimpleDateFormat("dd.MM.yyyy");
		SimpleDateFormat timeF = new SimpleDateFormat("HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		this.date = dateF.format(calendar.getTime());
		this.time = timeF.format(calendar.getTime());
	}
	
	//helper
	private double getSum(List<SoldItem> soldGoods) {
		double sum = 0;
		
		for(SoldItem row : soldGoods) {
			sum += row.getSum();
		}
		
		return sum;
	}
	
	
	@Override
	public Long getId() {
		return id;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getTime() {
		return time;
	}

	public double getPrice() {
		return price;
	}

//	public List<SoldItem> getSoldGoods() {
//		return soldGoods;
//	}

}
