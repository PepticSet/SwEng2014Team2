package ee.ut.math.tvt.salessystem.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

/**
 * This tests the equivalent of SaleTest/PurchaseTest in this POS.
 */
public class HistoryItemTest {
	
	private static StockItem stockItem1;
	private static StockItem stockItem2;
	private static StockItem stockItem3;
	
	private static SoldItem soldItem1;
	private static SoldItem soldItem2;
	private static SoldItem soldItem3;
	private static SoldItem soldItem4;
	
	private static HistoryItem historyItem1;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		stockItem1 = new StockItem((long) 0, "testitem1", "a test item", 29, 7);
		stockItem2 = new StockItem((long) 1, "testitem2", "a test item", 17, 10);
		stockItem3 = new StockItem((long) 13, "testitem3", "a test item", 31, 18);
		soldItem1 = new SoldItem(stockItem1, 7);
		soldItem2 = new SoldItem(stockItem2, 6);
		soldItem3 = new SoldItem(stockItem3, 1);
		soldItem4 = new SoldItem(stockItem3, 5);
	}

	@Before
	public void setUpBefore() throws Exception {
		historyItem1 = new HistoryItem();
		historyItem1.addItem(soldItem1);
		historyItem1.addItem(soldItem2);
		historyItem1.addItem(soldItem3);
	}

	@Test
	public void testAddSoldItemToEmpty() {
		HistoryItem emptyHistoryItem = new HistoryItem();
		List<SoldItem> soldGoods = emptyHistoryItem.getSoldGoods();
		assertEquals(0, soldGoods.size());
		emptyHistoryItem.addItem(soldItem1);
		soldGoods = emptyHistoryItem.getSoldGoods();
		assertEquals(1, soldGoods.size());
		assertEquals(soldGoods.get(0), soldItem1);
		
		assertNotNull(emptyHistoryItem.getSaleTime());
	}
	
	@Test
	public void testAddSoldItemToNonEmpty() {
		List<SoldItem> soldGoods = historyItem1.getSoldGoods();
		int sizeBefore = soldGoods.size();
		historyItem1.addItem(soldItem4);
		soldGoods = historyItem1.getSoldGoods();
		assertEquals(sizeBefore + 1, soldGoods.size());
		assertTrue(soldGoods.contains(soldItem4));
		
		assertNotNull(historyItem1.getSaleTime());
	}


	@Test
	public void testGetSumWithNoItems() {
		HistoryItem emptyHistoryItem = new HistoryItem();
		assertEquals(0, emptyHistoryItem.getSum(), 1e-6);
	}

	@Test
	public void testGetSumWithOneItem() {
		HistoryItem historyItem2 = new HistoryItem();
		historyItem2.addItem(soldItem2);
		assertEquals(soldItem2.getSum(), historyItem2.getSum(), 1e-6);
	}

	@Test
	public void testGetSumWithMultipleItems() {
		double sum = historyItem1.getSum();
		double actualSum = soldItem1.getSum() + soldItem2.getSum() + soldItem3.getSum();
		assertEquals(actualSum, sum, 1e-6);
	}
}
