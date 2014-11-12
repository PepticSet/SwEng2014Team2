package ee.ut.math.tvt.salessystem.domain.data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "HistoryItem")
public class HistoryItem implements Cloneable, DisplayableItem {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "price")
	private double price;
	
	@Column(name = "datev")
	private String date;
	
	@Column(name = "timev")
	private String time;

//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "HISTORYITEM_TO_SOLDITEMS",
//			joinColumns = @JoinColumn(name = "HISTORYITEM_ID",
//					referencedColumnName = "ID"),
//			inverseJoinColumns = @JoinColumn(name = "SOLDITEM_ID",
//					referencedColumnName = "ID"))
	//@Transient
	@OneToMany(mappedBy = "historyItem")
	private List<SoldItem> soldGoods;

	public HistoryItem(List<SoldItem> soldGoods) {
		super();
		this.soldGoods = soldGoods;
		
		for(SoldItem item : soldGoods) {
			item.setHistoryItem(this);
		}

		this.price = getSum(soldGoods);

		SimpleDateFormat dateF = new SimpleDateFormat("dd.MM.yyyy");
		SimpleDateFormat timeF = new SimpleDateFormat("HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		//reverse date for database
		String date = dateF.format(calendar.getTime());
		String[] dateArray = date.split("\\.");
		this.date = dateArray[2] + "." + dateArray[1] + "." + dateArray[0];
		
		this.time = timeF.format(calendar.getTime());
	}
	
	public HistoryItem() {
		
	}

	// helper
	private double getSum(List<SoldItem> soldGoods) {
		double sum = 0;

		for (SoldItem row : soldGoods) {
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

	public List<SoldItem> getSoldGoods() {
		return soldGoods;
	}

}
