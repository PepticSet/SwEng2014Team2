package ee.ut.math.tvt.salessystem.tests;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class StockItemTest {

	private static StockItem stockItem1;
	private static long id;
	private static String name;
	private static double price;
	private static String description;
	private static int quantity;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		id = 5;
		name = "testitem";
		price = 29;
		description = "a test item";
		quantity = 7;
		stockItem1 = new StockItem(id, name, description, price, quantity);
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testGetColumn() {
		assertEquals(stockItem1.getColumn(0), id);
		assertEquals(stockItem1.getColumn(1), name);
		assertEquals(stockItem1.getColumn(2), price);
		assertEquals(stockItem1.getColumn(3), quantity);
		thrown.expect(RuntimeException.class);
		stockItem1.getColumn(4);
	}

	@Test
	public void testClone() {
		StockItem clonedItem = (StockItem) stockItem1.clone();
		assertEquals(stockItem1.getId(), clonedItem.getId());
		assertEquals(stockItem1.getName(), clonedItem.getName());
		assertEquals(stockItem1.getPrice(), clonedItem.getPrice(), 1e-5);
		assertEquals(stockItem1.getDescription(), clonedItem.getDescription());
		assertEquals(stockItem1.getQuantity(), clonedItem.getQuantity());
	}

}
