package eg.edu.alexu.csd.oop.calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class HistoryDialog extends JDialog implements ActionListener {
	JButton OKbtn;

	public HistoryDialog(JFrame parent, String title, boolean modal) {
		super(parent, title, modal);

		JTextArea jtAreaHistory = new JTextArea(5, 21);
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		StringBuffer text = new StringBuffer();
		setBackground(Color.black);
		text.append("History:\n\n");

		for (int i = History.size() - 1; i >= 0; i--) {
			text.append(History.getAtIndex(i) + "\n");
		}
		text.append("\nDeveloped By:  Ahmed Elsayed");
		
		jtAreaHistory.setText(text.toString());
		jtAreaHistory.setFont(new Font("Times New Roman", 1, 15));
		jtAreaHistory.setEditable(false);
		
		p1.add(jtAreaHistory);
		p1.setBackground(Color.cyan);
		getContentPane().add(p1, BorderLayout.CENTER);
		OKbtn = new JButton(" OK ");
		OKbtn.addActionListener(this);
		p2.add(OKbtn);
		getContentPane().add(p2, BorderLayout.SOUTH);
		setLocation(408, 270);
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Window aboutDialog = e.getWindow();
				aboutDialog.dispose();
			}
		});
		pack();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == OKbtn) {
			this.dispose();
		}
	}
}
