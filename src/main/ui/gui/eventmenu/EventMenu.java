package ui.gui.eventmenu;

import model.SpendingEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// SpendingEvent menu window.
public class EventMenu extends JFrame implements ActionListener {
    List<JButton> butts;
    SpendingEvent spendingEvent;

    // EFFECTS: constructs a spendingEvent menu window for the given spendingEvent
    public EventMenu(SpendingEvent spendingEvent) {
        setVisible(true);
        setSize(400, 400);
        setLayout(new GridLayout(6, 1));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.butts = new ArrayList<>();
        this.spendingEvent = spendingEvent;

        setUpComponents();
        addListeners();
    }

    // MODIFIES: this.
    // EFFECTS: sets up the labels and buttons for the spendingEvent menu and stores them as fields.
    private void setUpComponents() {
        JLabel l1 = new JLabel("What would you like to do in this spendingEvent?");
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

    // EFFECTS: adds this as listeners for all buttons.
    private void addListeners() {
        for (JButton b : butts) {
            b.addActionListener(this);
        }
    }

    // EFFECTS: specifies the actions taken when each button is pressed.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == butts.get(0)) {
            new AddPerson(this.spendingEvent);
        }
        if (e.getSource() == butts.get(1)) {
            new AddExpense(this.spendingEvent);
        }
        if (e.getSource() == butts.get(2)) {
            new ShowPeople(this.spendingEvent.getPeople());
        }
        if (e.getSource() == butts.get(3)) {
            new ShowExpenses(this.spendingEvent.getExpenses());
        }
        if (e.getSource() == butts.get(4)) {
            new ShowSummary(this.spendingEvent);
        }
    }
}
