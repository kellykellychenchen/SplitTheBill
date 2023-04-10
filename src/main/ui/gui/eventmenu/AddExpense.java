package ui.gui.eventmenu;

import model.SpendingEvent;
import model.Expense;
import model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Pop-up window for creating a new expense.
public class AddExpense extends JFrame implements ActionListener {
    JTextField t0 = new JTextField(15);
    JTextField t1 = new JTextField(15);
    ArrayList<JRadioButton> paidChoices = new ArrayList<>();
    ArrayList<JCheckBox> sharedChoices = new ArrayList<>();
    SpendingEvent spendingEvent;

    // EFFECTS: constructs a window for adding a new Expense inside the given spendingEvent.
    public AddExpense(SpendingEvent spendingEvent) {
        setVisible(true);
        setSize(400, 200);
        setLayout(new GridLayout(5, 1)); //default CardLayout. FlowLayout, GridLayout, Null
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.spendingEvent = spendingEvent;

        setUpComponents(spendingEvent);
        pack();
    }


    // MODIFIES: this.
    // EFFECTS: sets up the labels and buttons for the add-expense window. Update the panel fields.
    private void setUpComponents(SpendingEvent spendingEvent) {
        JLabel costPromptLabel = new JLabel("How much did this expense cost? Enter a number.");
        JPanel costPanel = new JPanel();
        costPanel.add(costPromptLabel);
        costPanel.add(t0);
        add(costPanel);

        JLabel namePromptLabel = new JLabel("Enter the name of this expense.");
        JPanel namePanel = new JPanel();
        namePanel.add(namePromptLabel);
        namePanel.add(t1);
        add(namePanel);

        JPanel paidPanel = getPaidPanel(spendingEvent);
        add(paidPanel);

        JPanel sharedPanel = getSharedPanel(spendingEvent);
        add(sharedPanel);

        JButton butt = new JButton("OK");
        JPanel closePanel = new JPanel();
        closePanel.add(butt);
        add(closePanel);

        butt.addActionListener(this);
    }

    // MODIFIES: this.
    // EFFECTS: create radio-buttons for all people in the given spendingEvent. Update the paidChoices and paidPanel
    // fields.
    private JPanel getPaidPanel(SpendingEvent spendingEvent) {
        JLabel paidPromptLabel = new JLabel("Who paid for this expense?");
        ButtonGroup bg = new ButtonGroup();
        JPanel paidPanel = new JPanel();
        paidPanel.add(paidPromptLabel);
        for (Person p : spendingEvent.getPeople()) {
            JRadioButton b = new JRadioButton(p.getName());
            bg.add(b);
            paidChoices.add(b);
            paidPanel.add(b);
        }
        return paidPanel;
    }

    // MODIFIES: this.
    // EFFECTS: create check-boxes for all people in the given spendingEvent. Update the sharedChoices and sharedPanel
    // fields.
    private JPanel getSharedPanel(SpendingEvent spendingEvent) {
        JLabel sharedPromptLabel = new JLabel("Who shares the cost of this expense?");
        JPanel sharedPanel = new JPanel();
        sharedPanel.add(sharedPromptLabel);
        for (Person p : spendingEvent.getPeople()) {
            JCheckBox b = new JCheckBox(p.getName());
            sharedChoices.add(b);
            sharedPanel.add(b);
        }
        return sharedPanel;
    }

    // MODIFIES: SpendingEvent this.spendingEvent
    // EFFECTS: When button is pressed, create a new SpendingEvent with information that user entered, add it to
    // this.spendingEvent, and close the current window.
    @Override
    public void actionPerformed(ActionEvent e) {
        double amount = Double.parseDouble(t0.getText());
        String name = t1.getText();

        Person paidBy = this.spendingEvent.getPeople().get(0);
        for (int i = 0; i < paidChoices.size(); i++) {
            if (paidChoices.get(i).isSelected()) {
                paidBy = this.spendingEvent.getPeople().get(i);
            }
        }

        ArrayList<Person> sharedBy = new ArrayList<>();
        for (int i = 0; i < sharedChoices.size(); i++) {
            if (sharedChoices.get(i).isSelected()) {
                sharedBy.add(this.spendingEvent.getPeople().get(i));
            }
        }

        Expense exp1 = new Expense(name, amount, paidBy, sharedBy);
        this.spendingEvent.addExpense(exp1);
        this.spendingEvent.reCalculateBalance();
        dispose();
    }
}
