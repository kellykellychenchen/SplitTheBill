package ui.gui.eventmenu;

import model.Event;
import model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPerson extends JFrame implements ActionListener {
    JLabel l1 = new JLabel("Enter the person's name then press ADD.");
    JTextField t1 = new JTextField(15);
    JButton butt = new JButton("ADD");
    Event event;

    public AddPerson(Event event) {
        setVisible(true);
        setSize(400, 200);
        setLayout(new FlowLayout()); //default CardLayout. FlowLayout, GridLayout, Null
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.event = event;

        add(l1);
        add(t1);
        add(butt);
        butt.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = t1.getText();
        Person p1 = new Person(name);
        this.event.addPerson(p1);
        dispose();
    }
}
