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

public class AboutDialog extends JDialog implements ActionListener {
	JButton OKbtn;

	public AboutDialog(JFrame parent, String title, boolean modal) {
		super(parent, title, modal);
		
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JTextArea jtAreaAbout = new JTextArea(5, 21);
		StringBuffer text = new StringBuffer();
		setBackground(Color.black);
		
		text.append("Calculator Information\n\n");
		text.append("This is a simple calculator \nto test my oop and gui skils.\n\n");
		text.append("Developed By:  Ahmed Elsayed");
		
		jtAreaAbout.setText(text.toString());
		jtAreaAbout.setFont(new Font("Times New Roman", 1, 15));
		jtAreaAbout.setEditable(false);

		p1.add(jtAreaAbout);
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
