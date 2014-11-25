package ee.ut.math.tvt.salessystem.domain.data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
	
	@Column(name = "saleTime")
	private Date saleTime;

	@OneToMany(mappedBy = "historyItem")
	private List<SoldItem> soldGoods;

	public HistoryItem(List<SoldItem> soldGoods) {
		super();
		this.soldGoods = soldGoods;
		for(SoldItem item : soldGoods) {
			item.setHistoryItem(this);
		}
		this.saleTime = Calendar.getInstance().getTime();
	}
	
	public HistoryItem() {
		super();
		this.soldGoods = new ArrayList<SoldItem>();
		this.saleTime = Calendar.getInstance().getTime();
	}
	
	public void addItem(SoldItem soldItem) {
		soldItem.setHistoryItem(this);
		soldGoods.add(soldItem);
	}

	@Override
	public Long getId() {
		return id;
	}

	public Date getSaleTime() {
		return saleTime;
	}

	public double getSum() {
		double sum = 0;
		for (SoldItem row : soldGoods) {
			sum += row.getSum();
		}
		return sum;
	}

	public List<SoldItem> getSoldGoods() {
		return soldGoods;
	}

}
