package eg.edu.alexu.csd.oop.calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class GUI extends JFrame {

	protected JMenu jmenuFile, jmenuHelp, jmenuView;
	protected JMenuItem jmenuitemExit, jmenuitemAbout, jmenuitemHistory,
			jmenuitemSave, jmenuitemLoad;

	protected JLabel jlbOutput, jlbHistory, jlbSpace, jlbSpace2;
	protected JButton jbnButtons[];
	protected JPanel jplMaster, jplButtons, jplBackSpace, jplControl,
			jplHistory, jplAll;
	protected Font font = new Font("Curlz MT", 0, 20);
	protected Font font2 = new Font("Lucida Handwriting", 1, 20);
	protected Font font3 = new Font("Buxton Sketch", 0, 20);

	// ImageIcon icon = createImageIcon("/resources/java_icon.png","Java");
	public GUI() {
		// file
		jmenuFile = new JMenu("File");
		jmenuFile.setFont(font);
		jmenuFile.setMnemonic(KeyEvent.VK_F);
		// save
		jmenuitemSave = new JMenuItem("Save to a file");
		jmenuitemSave.setFont(font);
		jmenuitemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.CTRL_MASK));
		jmenuFile.add(jmenuitemSave);
		// load
		jmenuitemLoad = new JMenuItem("Load file");
		jmenuitemLoad.setFont(font);
		jmenuitemLoad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,
				ActionEvent.CTRL_MASK));
		jmenuFile.add(jmenuitemLoad);
		// exit
		jmenuitemExit = new JMenuItem("Exit");
		jmenuitemExit.setFont(font);
		jmenuitemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
				ActionEvent.CTRL_MASK));
		jmenuFile.add(jmenuitemExit);
		// view
		jmenuView = new JMenu("View");
		jmenuView.setFont(font);
		jmenuView.setMnemonic(KeyEvent.VK_V);
		// help
		jmenuHelp = new JMenu("Help");
		jmenuHelp.setFont(font);
		jmenuHelp.setMnemonic(KeyEvent.VK_H);
		// show history
		jmenuitemHistory = new JMenuItem("Show All History");
		jmenuitemHistory.setFont(font);
		jmenuView.add(jmenuitemHistory);
		// about
		jmenuitemAbout = new JMenuItem("About My Calculator");
		jmenuitemAbout.setFont(font);
		jmenuHelp.add(jmenuitemAbout);

		JMenuBar mb = new JMenuBar();
		mb.add(jmenuFile);
		mb.add(jmenuView);
		mb.add(jmenuHelp);
		setJMenuBar(mb);

		// Set frame layout manager
		setBackground(Color.gray);

		jlbOutput = new JLabel("0");
		jlbOutput.setHorizontalTextPosition(JLabel.RIGHT);
		jlbOutput.setBackground(Color.cyan);
		jlbOutput.setPreferredSize(new Dimension(300, 40));
		jlbOutput.setFont(font2);
		jlbOutput.setOpaque(true);

		jlbHistory = new JLabel("");
		jlbHistory.setHorizontalTextPosition(JLabel.RIGHT);
		jlbHistory.setBackground(Color.cyan);
		jlbHistory.setPreferredSize(new Dimension(300, 40));
		jlbHistory.setFont(font2);
		jlbHistory.setOpaque(true);

		jlbSpace = new JLabel();
		jlbSpace.setHorizontalTextPosition(JLabel.RIGHT);
		jlbSpace.setPreferredSize(new Dimension(300, 30));

		jlbSpace2 = new JLabel();
		jlbSpace2.setHorizontalTextPosition(JLabel.RIGHT);
		jlbSpace2.setPreferredSize(new Dimension(300, 30));

		// Add components to frame
		getContentPane().add(jlbHistory, BorderLayout.NORTH);
		getContentPane().add(jlbOutput, BorderLayout.CENTER);

		jplMaster = new JPanel();
		jplMaster.setLayout(new BorderLayout());
		jplButtons = new JPanel();
		jplButtons.setLayout(new GridLayout(4, 5, 15, 15));
		jplBackSpace = new JPanel();
		jplBackSpace.setLayout(new GridLayout(1, 2, 93, 30));
		jplControl = new JPanel();
		jplControl.setLayout(new GridLayout(1, 2, 15, 15));
		jplHistory = new JPanel();
		jplHistory.setLayout(new GridLayout(1, 3, 10, 10));
		jplAll = new JPanel();
		jplAll.setLayout(new BorderLayout());

		jbnButtons = new JButton[25];
		// container for Jbuttons
		for (int i = 0; i <= 9; i++) {
			jbnButtons[i] = new JButton(String.valueOf(i));
		}
		jbnButtons[10] = new JButton("+/-");
		jbnButtons[11] = new JButton(".");
		jbnButtons[12] = new JButton("=");
		jbnButtons[13] = new JButton("/");
		jbnButtons[14] = new JButton("*");
		jbnButtons[15] = new JButton("-");
		jbnButtons[16] = new JButton("+");
		jbnButtons[17] = new JButton("sqrt");
		jbnButtons[18] = new JButton("1/x");
		jbnButtons[19] = new JButton("%");
		jbnButtons[20] = new JButton("     Backspace    ");
		jplBackSpace.add(jbnButtons[20]);
		jbnButtons[21] = new JButton(" CE ");
		jplBackSpace.add(jbnButtons[21]);
		jbnButtons[22] = new JButton(" Previous ");
		jbnButtons[23] = new JButton(" Current ");
		jbnButtons[24] = new JButton(" Next ");

		// Setting all Numbered JButton's to Blue. The rest to Red
		for (int i = 0; i < jbnButtons.length; i++) {
			jbnButtons[i].setFont(font3);

			if (i < 10) {
				jbnButtons[i].setForeground(Color.blue);
				jbnButtons[i].setPreferredSize(new Dimension(50, 50));
			}

			else if (i < 20) {
				// jbnButtons[i].setBackground(new Color(0xFFFF00));
				jbnButtons[i].setForeground(Color.red);
			} else
				jbnButtons[i].setForeground(Color.BLACK);
		}
		// Add buttons to keypad panel starting at top left
		// First row
		for (int i = 7; i <= 9; i++) {
			jplButtons.add(jbnButtons[i]);
		}
		// add button / and sqrt
		jplButtons.add(jbnButtons[13]);
		jplButtons.add(jbnButtons[17]);
		// Second row
		for (int i = 4; i <= 6; i++) {
			jplButtons.add(jbnButtons[i]);
		}
		// add button * and 1/x
		jplButtons.add(jbnButtons[14]);
		jplButtons.add(jbnButtons[18]);
		// Third row
		for (int i = 1; i <= 3; i++) {
			jplButtons.add(jbnButtons[i]);
		}
		// adds button - and %
		jplButtons.add(jbnButtons[15]);
		jplButtons.add(jbnButtons[19]);
		// Fourth Row
		// add 0, +/-, ., +, and =
		jplButtons.add(jbnButtons[10]);
		jplButtons.add(jbnButtons[0]);
		jplButtons.add(jbnButtons[11]);
		jplButtons.add(jbnButtons[16]);
		jplButtons.add(jbnButtons[12]);

		jplMaster.add(jplBackSpace, BorderLayout.NORTH);
		jplMaster.add(jlbSpace2, BorderLayout.CENTER);
		jplMaster.add(jplButtons, BorderLayout.SOUTH);
		// next, prev, current
		jplHistory.add(jbnButtons[22]);
		jplHistory.add(jbnButtons[23]);
		jplHistory.add(jbnButtons[24]);

		jplAll.add(jplMaster, BorderLayout.NORTH);
		jplAll.add(jlbSpace, BorderLayout.CENTER);
		jplAll.add(jplHistory, BorderLayout.SOUTH);
		// Add components to frame
		getContentPane().add(jplAll, BorderLayout.SOUTH);
		requestFocus();
	}
}
