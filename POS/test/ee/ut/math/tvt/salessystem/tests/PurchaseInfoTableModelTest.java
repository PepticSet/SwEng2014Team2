package ee.ut.math.tvt.salessystem.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.exception.OutOfStockException;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;
import ee.ut.math.tvt.salessystem.ui.model.StockTableModel;

public class PurchaseInfoTableModelTest {


	private static StockItem stockItem1;
	private static StockItem stockItem2;
	private static StockItem stockItem3;
	private static StockItem stockItem1Duplicate;

	private static StockTableModel stm;
	
	private PurchaseInfoTableModel pitm;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		stockItem1 = new StockItem((long) 0, "testitem1", "a test item", 29, 7);
		stockItem2 = new StockItem((long) 1, "testitem2", "a test item", 17, 10);
		stockItem3 = new StockItem((long) 13, "testitem3", "a test item", 31, 18);
	}

	@Before
	public void setUpBefore() throws Exception {
		stm = new StockTableModel();
		stm.addItem(stockItem1);
		stm.addItem(stockItem2);
		stm.addItem(stockItem3);
		pitm = new PurchaseInfoTableModel(stm);
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testAddItem() {
		try {
			pitm.addItem(new SoldItem(stockItem1, 7));
		} catch (OutOfStockException e) {
			fail();
		}
	}

	@Test
	public void testAddMoreThanInStock() {
		try {
			pitm.addItem(new SoldItem(stockItem1, 10));
			fail();
		} catch (OutOfStockException e) {
		}
	}

	@Test
	public void testGetItemByIdWhenItemExists() {
		SoldItem soldItem = new SoldItem(stockItem1, 5);
		try {
			pitm.addItem(soldItem);
		} catch (OutOfStockException e) {
			fail();
		}
		SoldItem returnedItem = pitm.getItemById(soldItem.getId());
		assertEquals(soldItem, returnedItem);
	}
	
	@Test
	public void testNoDuplicates() {
		try {
			pitm.addItem(new SoldItem(stockItem1, 2));
			pitm.addItem(new SoldItem(stockItem1, 4));
		} catch (OutOfStockException e) {
			fail();
		}
		List<SoldItem> pitmRows = pitm.getTableRows();
		assertEquals(1, pitmRows.size());
		assertEquals(6, (int) pitmRows.get(0).getQuantity());
	}

}
