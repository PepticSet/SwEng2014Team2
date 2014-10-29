package ee.ut.math.tvt.salessystem.ui;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.ui.model.DetailedHistoryTableModel;
import ee.ut.math.tvt.salessystem.ui.model.HistoryTableModel;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTextField;

public class PaymentDialog extends JDialog {
	
	private SalesSystemModel model;
	private List<SoldItem> purchaseTable;
	
	private JTextField textFieldPayment;
	private JLabel lblChangeAmount;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private JTextField textField;

	public PaymentDialog(List<SoldItem> goods, SalesSystemModel model) {
		
		this.purchaseTable = goods;
		this.model = model;
		
		setTitle("Payment processing");
		setBounds(100, 100, 300, 214);
		getContentPane().setLayout(new BorderLayout(0, 0));
		JPanel contentPanel = new JPanel();
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblSum = new JLabel("Sum:");
		contentPanel.add(lblSum, "4, 4");
		
		JLabel lblSumValue = new JLabel(getSum());
		contentPanel.add(lblSumValue, "12, 4");
		
		JLabel lblPaymentReceived = new JLabel("Payment received:");
		contentPanel.add(lblPaymentReceived, "4, 8");
		
		textFieldPayment = new JTextField();
		contentPanel.add(textFieldPayment, "12, 8, fill, default");
		textFieldPayment.setColumns(10);
		
		JLabel lblChange = new JLabel("Change: ");
		contentPanel.add(lblChange, "4, 12");
		
		lblChangeAmount = new JLabel("0.0");
		contentPanel.add(lblChangeAmount, "12, 12");
		
		

		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		
		JButton btnCompute = new JButton("Compute");
		buttonPane.add(btnCompute);
		btnCompute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				computeButtonClicked();
			}
		});
		
		
		JButton btnAccept = new JButton("Accept");
		buttonPane.add(btnAccept);
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acceptButtonClicked();
			}
		});
		
		
		JButton btnCancel = new JButton("Cancel");
		buttonPane.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	
	private void computeButtonClicked() {
		try {
			double payment = Double.parseDouble(textFieldPayment.getText());
			double sum = Double.parseDouble(getSum());
			if (payment >= sum) {
				lblChangeAmount.setText(String.valueOf((payment - sum)));
			} else {
				JOptionPane.showMessageDialog(null, "Payment insufficient!",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Payment must be a number!.",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void acceptButtonClicked() {
		
		model.getHistoryTableModel().addItem(new HistoryItem(purchaseTable));
		
		for(SoldItem item : purchaseTable) {
			model.getDetailedHistoryTableModel().addItem(item);
		}
		
		//XXX remove from stock
		
		dispose();
	}
	
	private String getSum() {
		double sum = 0;
		
		for(SoldItem row : purchaseTable) {
			sum += row.getSum();
		}
		
		return Double.toString(sum);
	}

}
