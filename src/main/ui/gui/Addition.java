package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Addition extends JFrame implements ActionListener {

    JTextField t1 = new JTextField(20);
    JTextField t2 = new JTextField(20);
    JButton b = new JButton("OK");
    JLabel l = new JLabel("Result");

    public Addition() {
        super("Add");
        setVisible(true);
        setSize(400,400);
        setLayout(new FlowLayout()); //default CardLayout. FlowLayout, GridLayout, Null
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        b.addActionListener(this); //ActionListener is an interface.

        add(t1);
        add(t2);
        add(b);
        add(l);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int num1 = Integer.parseInt(t1.getText());
        int num2 = Integer.parseInt(t2.getText());

        int value = num1 + num2;
        l.setText(Integer.toString(value));
    }
}
