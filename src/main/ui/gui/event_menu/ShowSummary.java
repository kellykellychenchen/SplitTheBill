package ui.gui.event_menu;

import model.Event;
import model.Expense;
import model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static java.lang.Math.abs;

public class ShowSummary extends JFrame implements ActionListener {
    JLabel l1 = new JLabel("This is a cost summary of this event.");
    JLabel totalCostLabel = new JLabel();
    JButton butt = new JButton("CLOSE");
    JPanel ptop = new JPanel();
    JPanel pmid = new JPanel();
    JPanel pmid2 = new JPanel();
    JPanel pbot = new JPanel();
    Event event;

    public ShowSummary(Event event) {
        setVisible(true);
        setSize(400, 400);
        setLayout(new GridLayout(4,1)); //default CardLayout. FlowLayout, GridLayout, Null
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.event = event;

        ptop.add(l1);

        double total = event.calcTotalCost();
        totalCostLabel.setText("The total cost of all expenses in this event is $" + String.format("%.2f", total));
        pmid.add(totalCostLabel);

        BoxLayout layout = new BoxLayout(pmid2, BoxLayout.Y_AXIS);
        pmid2.setLayout(layout);

        for (Person p : event.getPeople()) {
            JLabel personLabel = new JLabel();
            double balance = p.getBalance();
            String personName = p.getName();
            if (balance == 0) {
                personLabel.setText(personName + "has no outstanding balance.");
            } else if (balance < 0) {
                balance = abs(balance);
                personLabel.setText(personName + " owes $" + String.format("%.2f", balance));
            } else {
                personLabel.setText(personName + " should receive $" + String.format("%.2f", balance));
            }
            personLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            pmid2.add(personLabel);
        }

        pbot.add(butt);

        add(ptop);
        add(pmid);
        add(pmid2);
        add(pbot);

        pack();
        butt.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
    }
}
