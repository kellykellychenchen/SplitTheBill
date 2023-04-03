package ui.gui.eventmenu;

import model.Event;
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
    Event event;

    // EFFECTS: constructs a window for adding a new Expense inside the given event.
    public AddExpense(Event event) {
        setVisible(true);
        setSize(400, 200);
        setLayout(new GridLayout(5, 1)); //default CardLayout. FlowLayout, GridLayout, Null
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.event = event;

        setUpComponents(event);
        pack();
    }


    // MODIFIES: this.
    // EFFECTS: sets up the labels and buttons for the add-expense window. Update the panel fields.
    private void setUpComponents(Event event) {
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

        JPanel paidPanel = getPaidPanel(event);
        add(paidPanel);

        JPanel sharedPanel = getSharedPanel(event);
        add(sharedPanel);

        JButton butt = new JButton("OK");
        JPanel closePanel = new JPanel();
        closePanel.add(butt);
        add(closePanel);

        butt.addActionListener(this);
    }

    // MODIFIES: this.
    // EFFECTS: create radio-buttons for all people in the given event. Update the paidChoices and paidPanel fields.
    private JPanel getPaidPanel(Event event) {
        JLabel paidPromptLabel = new JLabel("Who paid for this expense?");
        ButtonGroup bg = new ButtonGroup();
        JPanel paidPanel = new JPanel();
        paidPanel.add(paidPromptLabel);
        for (Person p : event.getPeople()) {
            JRadioButton b = new JRadioButton(p.getName());
            bg.add(b);
            paidChoices.add(b);
            paidPanel.add(b);
        }
        return paidPanel;
    }

    // MODIFIES: this.
    // EFFECTS: create check-boxes for all people in the given event. Update the sharedChoices and sharedPanel fields.
    private JPanel getSharedPanel(Event event) {
        JLabel sharedPromptLabel = new JLabel("Who shares the cost of this expense?");
        JPanel sharedPanel = new JPanel();
        sharedPanel.add(sharedPromptLabel);
        for (Person p : event.getPeople()) {
            JCheckBox b = new JCheckBox(p.getName());
            sharedChoices.add(b);
            sharedPanel.add(b);
        }
        return sharedPanel;
    }

    // MODIFIES: Event this.event
    // EFFECTS: When button is pressed, create a new Event with information that user entered, add it to this.event,
    // and close the current window.
    @Override
    public void actionPerformed(ActionEvent e) {
        double amount = Double.parseDouble(t0.getText());
        String name = t1.getText();

        Person paidBy = this.event.getPeople().get(0);
        for (int i = 0; i < paidChoices.size(); i++) {
            if (paidChoices.get(i).isSelected()) {
                paidBy = this.event.getPeople().get(i);
            }
        }

        ArrayList<Person> sharedBy = new ArrayList<>();
        for (int i = 0; i < sharedChoices.size(); i++) {
            if (sharedChoices.get(i).isSelected()) {
                sharedBy.add(this.event.getPeople().get(i));
            }
        }

        Expense exp1 = new Expense(name, amount, paidBy, sharedBy);
        this.event.addExpense(exp1);
        this.event.reCalculateBalance();
        dispose();
    }
}
