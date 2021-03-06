package ee.ut.math.tvt.salessystem.domain.controller.impl;

import java.util.List;

import javax.swing.WindowConstants;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.ui.PaymentDialog;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;

/**
 * Implementation of the sales domain controller.
 */
public class SalesDomainControllerImpl implements SalesDomainController {

	@Override
	public void submitCurrentPurchase(List<SoldItem> goods,
			SalesSystemModel model) throws VerificationFailedException {

		PaymentDialog payment = new PaymentDialog(goods, model);
		payment.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		payment.setVisible(true);

		// XXX - Save purchase
	}

	@Override
	public void cancelCurrentPurchase() throws VerificationFailedException {
		// XXX - Cancel current purchase
	}

	@Override
	public void startNewPurchase() throws VerificationFailedException {
		// XXX - Start new purchase
	}

	@Override
	public List<StockItem> loadWarehouseState() {
//		List<StockItem> dataset = new ArrayList<StockItem>();
//
//		StockItem chips = new StockItem(1l, "Lays chips", "Potato chips", 11.0,
//				5);
//		StockItem chupaChups = new StockItem(2l, "Chupa-chups", "Sweets", 8.0,
//				8);
//		StockItem frankfurters = new StockItem(3l, "Frankfurters",
//				"Beer sauseges", 15.0, 12);
//		StockItem beer = new StockItem(4l, "Free Beer", "Student's delight",
//				0.0, 100);
		
		// uus
		return HibernateUtil.currentSession().createQuery("from StockItem").list();
		//
		
	}
	
	public List<HistoryItem> loadHistory() {
		return HibernateUtil.currentSession().createQuery("from HistoryItem").list();
	}

	@Override
	public void submitCurrentPurchase(List<SoldItem> goods)
			throws VerificationFailedException {
		// TODO Auto-generated method stub

	}

	@Override
	public void endSession() {
		HibernateUtil.closeSession();

	}
}
