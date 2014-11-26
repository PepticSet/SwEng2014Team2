package ee.ut.math.tvt.salessystem.tests;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.junit.BeforeClass;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.HistoryTableModel;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;

public class HistoryTableModelTest {
	
	private static StockItem stockItem1;
	private static StockItem stockItem2;
	private static SoldItem soldItem1;
	private static SoldItem soldItem2;
	private static HistoryItem historyItem;
	private static HistoryTableModel historyTableModel;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		stockItem1 = new StockItem((long) 12, "TestStockItem", "Test", 10);
		soldItem1 = new SoldItem(stockItem1, 10);
		stockItem2 = new StockItem((long) 13, "TestStockItem2", "StillTesting", 5);
		soldItem2 = new SoldItem(stockItem2, 5);
		historyItem = new HistoryItem();
		historyItem.addItem(soldItem1);
		historyItem.addItem(soldItem2);
	}

	@Test
	public void testAddItem() {
		Session sess = HibernateUtil.currentSession();
		int count1 = ((Long)sess.createQuery("select count(*) from HistoryItem").uniqueResult()).intValue(); //loe üle HistoryItemi read
		
		historyTableModel = new HistoryTableModel();
		historyTableModel.addItem(historyItem);
		int count2 = ((Long)sess.createQuery("select count(*) from HistoryItem").uniqueResult()).intValue();
		assertEquals(count1 + 1, count2); //võrdle, kas 1 rida tuli juurde
	}
	

}
