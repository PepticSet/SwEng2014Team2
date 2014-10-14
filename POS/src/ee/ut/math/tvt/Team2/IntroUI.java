package ee.ut.math.tvt.Team2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class IntroUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(IntroUI.class);

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IntroUI frame = new IntroUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public IntroUI() {
		setTitle("Team 2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(
				new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC, }, new RowSpec[] {
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
						FormFactory.DEFAULT_ROWSPEC, }));

		JLabel lblTeamName = new JLabel("Team name:");
		contentPane.add(lblTeamName, "2, 2");

		JLabel teamNameDetails = new JLabel("");
		contentPane.add(teamNameDetails, "4, 2");

		JLabel lblTeamLeader = new JLabel("Team leader:");
		contentPane.add(lblTeamLeader, "2, 4");

		JLabel teamLeaderDetails = new JLabel("");
		contentPane.add(teamLeaderDetails, "4, 4");

		JLabel lblTeamLeaderEmail = new JLabel("Team leader email:");
		contentPane.add(lblTeamLeaderEmail, "2, 6");

		JLabel teamLeaderEmailDetails = new JLabel("");
		contentPane.add(teamLeaderEmailDetails, "4, 6");

		JLabel lblTeamMembers = new JLabel("Team members:");
		contentPane.add(lblTeamMembers, "2, 8");

		JLabel teamMembersDetails = new JLabel("");
		contentPane.add(teamMembersDetails, "4, 8");

		JLabel lblLogo = new JLabel("Logo:");
		contentPane.add(lblLogo, "2, 10");

		JLabel logo = new JLabel("");
		contentPane.add(logo, "4, 10");

		JLabel lblVersion = new JLabel("Version:");
		contentPane.add(lblVersion, "2, 12");

		JLabel versionDetails = new JLabel("");
		contentPane.add(versionDetails, "4, 12");
	}

}
