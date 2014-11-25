package ee.ut.math.tvt.salessystem.tests;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.exception.OutOfStockException;
import ee.ut.math.tvt.salessystem.ui.model.StockTableModel;

public class StockTableModelTest {

	private static StockItem stockItem1;
	private static StockItem stockItem2;
	private static StockItem stockItem3;
	private static StockItem stockItem1Duplicate;

	private StockTableModel stm;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		stockItem1 = new StockItem((long) 0, "testitem1", "a test item", 29, 7);
		stockItem2 = new StockItem((long) 1, "testitem2", "a test item", 17, 10);
		stockItem3 = new StockItem((long) 13, "testitem3", "a test item", 31, 18);
		stockItem1Duplicate = new StockItem((long) 4, "testitem1", "a test item", 11, 6);
	}

	@Before
	public void setUpBefore() throws Exception {
		stm = new StockTableModel();
		stm.addItem(stockItem1);
		stm.addItem(stockItem2);
		stm.addItem(stockItem3);
	}

	@Test
	public void testGetItemByIdWhenItemExists() {
		StockItem returnedItem = stm.getItemById(stockItem1.getId());
		assertEquals(stockItem1.getId(), returnedItem.getId());
		assertEquals(stockItem1.getName(), returnedItem.getName());
		returnedItem = stm.getItemById(stockItem2.getId());
		assertEquals(stockItem2.getId(), returnedItem.getId());
		assertEquals(stockItem2.getName(), returnedItem.getName());
		returnedItem = stm.getItemById(stockItem3.getId());
		assertEquals(stockItem3.getId(), returnedItem.getId());
		assertEquals(stockItem3.getName(), returnedItem.getName());
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testGetItemByIdWhenThrowsException() {
		thrown.expect(NoSuchElementException.class);
		stm.getItemById(Long.MAX_VALUE);
	}

	@Test
	public void testValidateNameUniqueness() {
		stm.addItem(stockItem1Duplicate);

		List<String> nameList = new LinkedList<String>();
		for (StockItem si : stm.getTableRows()) {
			nameList.add(si.getName());
		}
		Set<String> nameSet = new HashSet<String>(nameList);
		assertEquals(nameList.size(), nameSet.size());
	}

	@Test
	public void testHasEnoughInStock() {
		SoldItem soldItem = new SoldItem(stockItem1, stockItem1.getQuantity());
		stm.removeItem(soldItem);

		thrown.expect(OutOfStockException.class);
		soldItem = new SoldItem(stockItem1, 1);
		stm.removeItem(soldItem);
	}

}
