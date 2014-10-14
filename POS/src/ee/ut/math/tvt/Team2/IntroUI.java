package ee.ut.math.tvt.Team2;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.Logger;

import com.jgoodies.looks.windows.WindowsLookAndFeel;

public class IntroUI extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger(IntroUI.class);

	public IntroUI() {
		setTitle("Intro UI for Team 2");

		// set L&F to the nice Windows style
		try {
			UIManager.setLookAndFeel(new WindowsLookAndFeel());
		} catch (UnsupportedLookAndFeelException e1) {
			log.warn(e1.getMessage());
		}

		drawWidgets();

		// size & location
		int width = 400;
		int height = 600;
		setSize(width, height);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screen.width - width) / 2, (screen.height - height) / 2);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	private void drawWidgets() {
		JTabbedPane tabbedPane = new JTabbedPane();

		getContentPane().add(tabbedPane);
	}
}
