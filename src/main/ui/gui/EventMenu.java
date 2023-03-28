package ui.gui;

import model.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EventMenu extends JFrame implements ActionListener {
    JLabel l1 = new JLabel("What would you like to do in this event?");
    List<JButton> butts;

    public EventMenu(Event event) {
        setVisible(true);
        setSize(400, 400);
        setLayout(new FlowLayout()); //default CardLayout. FlowLayout, GridLayout, Null
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.butts = new ArrayList<>();

        add(l1);
        JButton b0 = new JButton("Add person");
        butts.add(b0);
        JButton b1 = new JButton("Add expense");
        butts.add(b1);
        JButton b2 = new JButton("View people");
        butts.add(b2);
        JButton b3 = new JButton("View expenses");
        butts.add(b3);
        JButton b4 = new JButton("View cost summary");
        butts.add(b4);

        add(b0);
        add(b1);
        add(b2);
        add(b3);
        add(b4);

        b0.addActionListener(this);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == butts.get(0)) {

        }
        if (e.getSource() == butts.get(1)) {

        }
        if (e.getSource() == butts.get(2)) {

        }
        if (e.getSource() == butts.get(3)) {

        }
        if (e.getSource() == butts.get(4)) {

        }
    }
}
