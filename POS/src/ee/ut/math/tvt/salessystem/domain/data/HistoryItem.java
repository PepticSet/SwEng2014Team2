package ee.ut.math.tvt.salessystem.domain.data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity(name = "HistoryItem")
public class HistoryItem implements Cloneable, DisplayableItem {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "price")
	private double price;
	
	@Column(name = "date")
	private String date;
	
	@Column(name = "time")
	private String time;

	@ManyToMany
	@JoinTable(name = "HistoryItem_SoldGoods",
			joinColumns = @JoinColumn(name = "HistoryItem_ID",
					referencedColumnName = "ID"),
			inverseJoinColumns = @JoinColumn(name = "SoldItem_ID",
					referencedColumnName = "ID"))
	private List<SoldItem> soldGoods;

	public HistoryItem(List<SoldItem> soldGoods) {
		super();
		this.soldGoods = soldGoods;

		this.price = getSum(soldGoods);

		SimpleDateFormat dateF = new SimpleDateFormat("dd.MM.yyyy");
		SimpleDateFormat timeF = new SimpleDateFormat("HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		this.date = dateF.format(calendar.getTime());
		this.time = timeF.format(calendar.getTime());
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
