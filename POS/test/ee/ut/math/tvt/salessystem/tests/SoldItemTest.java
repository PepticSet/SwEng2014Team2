package ee.ut.math.tvt.salessystem.tests;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class SoldItemTest {

	private static StockItem stockItem1;
	private static long id;
	private static String name;
	private static double price;
	private static String description;
	private static int stockItemQuantity;

	private static SoldItem soldItem1;
	private static int soldItem1Quantity;
	private static SoldItem soldItem2;

	private static double eps = 1e-3;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		id = 5;
		name = "testitem";
		price = 29;
		description = "a test item";
		stockItemQuantity = 7;
		stockItem1 = new StockItem(id, name, description, price, stockItemQuantity);

		soldItem1Quantity = 5;
		soldItem1 = new SoldItem(stockItem1, soldItem1Quantity);

		soldItem2 = new SoldItem(stockItem1, 0);
	}

	@Test
	public void testGetSum() {
		double sum = soldItem1.getSum();
		assertEquals(soldItem1Quantity * price, sum, 1e-5);
	}

	@Test
	public void testGetSumWithZeroQuantity() {
		assertEquals(0, soldItem2.getSum(), 1e-5);
	}

}
