package ee.ut.math.tvt.Team2;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class IntroUI extends JFrame {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(IntroUI.class);

	private JPanel contentPane;

	Configuration applicationProperties;
	Configuration versionProperties;

	private void loadConfig() {
		try {
			applicationProperties = new PropertiesConfiguration(
					"application.properties");
			versionProperties = new PropertiesConfiguration(
					"version.properties");
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public IntroUI() {
		loadConfig();

		setTitle(applicationProperties.getString("TeamName"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
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

		JLabel teamNameDetails = new JLabel(
				applicationProperties.getString("TeamName"));
		contentPane.add(teamNameDetails, "4, 2");

		JLabel lblTeamLeader = new JLabel("Team leader:");
		contentPane.add(lblTeamLeader, "2, 4");

		JLabel teamLeaderDetails = new JLabel(
				applicationProperties.getString("TeamLeader"));
		contentPane.add(teamLeaderDetails, "4, 4");

		JLabel lblTeamLeaderEmail = new JLabel("Team leader email:");
		contentPane.add(lblTeamLeaderEmail, "2, 6");

		JLabel teamLeaderEmailDetails = new JLabel(
				applicationProperties.getString("TeamLeaderEmail"));
		contentPane.add(teamLeaderEmailDetails, "4, 6");

		JLabel lblTeamMembers = new JLabel("Team members:");
		contentPane.add(lblTeamMembers, "2, 8");

		JLabel teamMembersDetails = new JLabel(StringUtils.join(
				applicationProperties.getStringArray("TeamMembers"), ", "));
		contentPane.add(teamMembersDetails, "4, 8");

		JLabel lblLogo = new JLabel("Logo:");
		contentPane.add(lblLogo, "2, 10");

		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(applicationProperties
					.getString("TeamLogo")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon imageIcon = new ImageIcon(img.getScaledInstance(200, 100,
				Image.SCALE_SMOOTH));
		JLabel logo = new JLabel(imageIcon);
		logo.setSize(100, 100);
		contentPane.add(logo, "4, 10");

		JLabel lblVersion = new JLabel("Version:");
		contentPane.add(lblVersion, "2, 12");

		JLabel versionDetails = new JLabel(
				versionProperties.getString("build.number"));
		contentPane.add(versionDetails, "4, 12");
	}

}
