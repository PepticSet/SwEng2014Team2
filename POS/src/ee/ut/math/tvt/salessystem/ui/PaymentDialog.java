package ee.ut.math.tvt.salessystem.ui;

import javax.swing.JDialog;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JTextField;

public class PaymentDialog extends JDialog {
	
	//private final JPanel contentPanel = new JPanel();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;

	public PaymentDialog() {
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
		
		JLabel label = new JLabel("");
		contentPanel.add(label, "12, 4");
		
		JLabel lblPaymentReceived = new JLabel("Payment received:");
		contentPanel.add(lblPaymentReceived, "4, 8");
		
		textField = new JTextField();
		contentPanel.add(textField, "12, 8, fill, default");
		textField.setColumns(10);
		
		JLabel lblChange = new JLabel("Change: ");
		contentPanel.add(lblChange, "4, 12");
		
		JLabel lblNewLabel = new JLabel("");
		contentPanel.add(lblNewLabel, "12, 12");
		
		

		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton btnAccept = new JButton("Accept");
		buttonPane.add(btnAccept);
		
		JButton btnCancel = new JButton("Cancel");
		buttonPane.add(btnCancel);
	}

}
