// Source File Name:   Sss.java

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class FirstFrame extends JFrame
    implements ActionListener
{

    FirstFrame(String s)
    {
        super(s);
        p = new JPanel();
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        c = getContentPane();
        addWindowListener(new MyWindowAdapter(this));
        setSize(400, 300);
        unamel = new JLabel("Username:", 0);
        pwordl = new JLabel("Password:", 0);
        unamel.setFont(new Font("Sans", 1, 15));
        pwordl.setFont(new Font("Sans", 1, 15));
        username = new TextField(10);
        password = new TextField(10);
        password.setEchoChar('*');
        submit = new JButton("Submit");
        cancel = new JButton("Cancel");
        submit.setBackground(new Color(130, 200, 220));
        cancel.setBackground(new Color(130, 200, 220));
        submit.setMnemonic('S');
        cancel.setMnemonic('C');
        p1.setLayout(new FlowLayout());
        p2.setLayout(new FlowLayout());
        p3.setLayout(new FlowLayout());
        p.setLayout(new GridLayout(3, 1));
        p1.add(unamel);
        p1.add(username);
        p2.add(pwordl);
        p2.add(password);
        p3.add(submit);
        p3.add(cancel);
        p.add(p1);
        p.add(p2);
        p.add(p3);
        c.add(p);
        username.addActionListener(this);
        password.addActionListener(this);
        cancel.addActionListener(this);
        submit.addActionListener(this);
        show();
    }

    public void actionPerformed(ActionEvent actionevent)
    {
        String s = actionevent.getActionCommand();
        String s1 = username.getText();
        String s2 = password.getText();
        if(s.equals("Submit"))
        {
            if(s1.equals("hamada") && s2.equals("123456"))
            {
                setVisible(false);
                MainFrame mainframe = new MainFrame("Software Security System");
            } else
            if(!s1.equals("raghunandan"))
            {
                JOptionPane joptionpane = new JOptionPane();
                JOptionPane _tmp = joptionpane;
                JOptionPane.showMessageDialog(this, "Unauthorised User", "Message", 3);
            } else
            if(!s2.equals("bharath"))
            {
                JOptionPane joptionpane1 = new JOptionPane();
                JOptionPane _tmp1 = joptionpane1;
                JOptionPane.showMessageDialog(this, "Invalid Password", "Message", 3);
            }
        } else
        if(s.equals("Cancel"))
            setVisible(false);
    }

    TextField username;
    TextField password;
    JLabel unamel;
    JLabel pwordl;
    JButton submit;
    JButton cancel;
    JPanel p;
    JPanel p1;
    JPanel p2;
    JPanel p3;
    Container c;
}