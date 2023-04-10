package ui.gui.eventmenu;

import model.SpendingEvent;
import model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Math.abs;

// A window showing the cost summary.
public class ShowSummary extends JFrame implements ActionListener {
    JPanel balanceSummaryPanel = new JPanel();
    SpendingEvent spendingEvent;

    // EFFECTS: constructs a window showing the cost summary of the given spendingEvent.
    public ShowSummary(SpendingEvent spendingEvent) {
        setVisible(true);
        setSize(400, 400);
        setLayout(new GridLayout(4, 1)); //default CardLayout. FlowLayout, GridLayout, Null
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.spendingEvent = spendingEvent;

        setUpComponents(spendingEvent);
        pack();
    }

    // EFFECTS: sets up the labels and buttons for the show cost summary screen.
    private void setUpComponents(SpendingEvent spendingEvent) {
        JPanel titlePanel = new JPanel();
        JPanel totalCostPanel = new JPanel();
        JPanel closePanel = new JPanel();

        JLabel l1 = new JLabel("This is a cost summary of this spendingEvent.");
        titlePanel.add(l1);

        JLabel totalCostLabel = new JLabel();
        totalCostLabel.setText("The total cost of all expenses in this spendingEvent is $"
                + String.format("%.2f", spendingEvent.calcTotalCost()));
        totalCostPanel.add(totalCostLabel);

        BoxLayout layout = new BoxLayout(balanceSummaryPanel, BoxLayout.Y_AXIS);
        balanceSummaryPanel.setLayout(layout);
        getBalanceForEachPerson(spendingEvent);

        JButton butt = new JButton("CLOSE");
        closePanel.add(butt);

        add(titlePanel);
        add(totalCostPanel);
        add(balanceSummaryPanel);
        add(closePanel);

        butt.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: calculate the balance for each person in the given spendingEvent and update the balanceSummaryPanel.
    private void getBalanceForEachPerson(SpendingEvent spendingEvent) {
        for (Person p : spendingEvent.getPeople()) {
            JLabel personLabel = new JLabel();
            double balance = p.getBalance();
            String personName = p.getName();
            if (balance == 0) {
                personLabel.setText(personName + " has no outstanding balance.");
            } else if (balance < 0) {
                balance = abs(balance);
                personLabel.setText(personName + " owes $" + String.format("%.2f", balance));
            } else {
                personLabel.setText(personName + " should receive $" + String.format("%.2f", balance));
            }
            personLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            balanceSummaryPanel.add(personLabel);
        }
    }

    // EFFECTS: when button is pressed, close the current window.
    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
    }
}
