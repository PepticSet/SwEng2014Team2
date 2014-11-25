package ee.ut.math.tvt.salessystem.tests;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class SoldItemTest {
	
	private static StockItem stockItem1;
	private static SoldItem soldItem1;
	private static SoldItem soldItem2;
	private static int soldItem1Quantity;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		stockItem1 = new StockItem((long) 5, "testitem", "a test item", 29, 7);

		soldItem1Quantity = 5;
		soldItem1 = new SoldItem(stockItem1, soldItem1Quantity);

		soldItem2 = new SoldItem(stockItem1, 0);
	}

	@Test
	public void testGetSum() {
		double sum = soldItem1.getSum();
		assertEquals(soldItem1Quantity * stockItem1.getPrice(), sum, 1e-5);
	}

	@Test
	public void testGetSumWithZeroQuantity() {
		assertEquals(0, soldItem2.getSum(), 1e-5);
	}

}
