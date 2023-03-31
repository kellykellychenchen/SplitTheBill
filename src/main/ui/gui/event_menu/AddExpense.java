package ui.gui.event_menu;

import model.Event;
import model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddExpense extends JFrame implements ActionListener {
    JLabel l0 = new JLabel("How much did this expense cost? Enter a number.");
    JTextField t0 = new JTextField(15);
    JPanel p0 = new JPanel();

    JLabel l1 = new JLabel("Enter the name of this expense.");
    JTextField t1 = new JTextField(15);
    JPanel p1 = new JPanel();

    JLabel l2 = new JLabel("Who paid for this expense?");
    ButtonGroup bg = new ButtonGroup();
    JPanel p2 = new JPanel();

    JLabel l3 = new JLabel("Who shares the cost of this expense?");
    JPanel p3 = new JPanel();

    JButton butt = new JButton("OK");
    Event event;

    public AddExpense(Event event) {
        setVisible(true);
        setSize(400, 200);
        setLayout(new FlowLayout()); //default CardLayout. FlowLayout, GridLayout, Null
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.event = event;

        p0.add(l0);
        p0.add(t0);
        add(p0);

        p1.add(l1);
        p1.add(t1);
        add(p1);

        p2.add(l2);
        for (Person p : event.getPeople()) {
            JRadioButton b = new JRadioButton(p.getName());
            bg.add(b);
            p2.add(b);
        }
        add(p2);

        p3.add(l3);
        for (Person p : event.getPeople()) {
            JCheckBox b = new JCheckBox(p.getName());
            p3.add(b);
        }
        add(p3);

        add(butt);

        butt.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        double amount = Double.parseDouble(t0.getText());
        String name = t1.getText();


        Person p1 = new Person(name);
        this.event.addPerson(p1);
        dispose();
    }


}
