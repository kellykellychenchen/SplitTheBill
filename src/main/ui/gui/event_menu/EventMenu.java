package ui.gui.event_menu;

import model.Event;
import ui.gui.event_menu.AddPerson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EventMenu extends JFrame implements ActionListener {
    JLabel l1 = new JLabel("What would you like to do in this event?");
    List<JButton> butts;
    Event event;

    public EventMenu(Event event) {
        setVisible(true);
        setSize(400, 400);
        setLayout(new GridLayout(6,1)); //default CardLayout. FlowLayout, GridLayout, Null
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.butts = new ArrayList<>();
        this.event = event;

        setUpComponents();
        addListeners();
    }

    private void setUpComponents() {
        JButton b0 = new JButton("Add person");
        JButton b1 = new JButton("Add expense");
        JButton b2 = new JButton("View people");
        JButton b3 = new JButton("View expenses");
        JButton b4 = new JButton("View cost summary");
        butts.add(b0);
        butts.add(b1);
        butts.add(b2);
        butts.add(b3);
        butts.add(b4);
        add(l1);
        add(b0);
        add(b1);
        add(b2);
        add(b3);
        add(b4);
    }

    private void addListeners() {
        for (JButton b : butts) {
            b.addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == butts.get(0)) {
            new AddPerson(this.event);
        }
        if (e.getSource() == butts.get(1)) {
            new AddExpense(this.event);
        }
        if (e.getSource() == butts.get(2)) {

        }
        if (e.getSource() == butts.get(3)) {

        }
        if (e.getSource() == butts.get(4)) {

        }
    }
}
