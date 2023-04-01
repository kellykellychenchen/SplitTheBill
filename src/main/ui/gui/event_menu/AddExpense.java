package ui.gui.event_menu;

import model.Event;
import model.Expense;
import model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddExpense extends JFrame implements ActionListener {
    JLabel costPromptLabel = new JLabel("How much did this expense cost? Enter a number.");
    JTextField t0 = new JTextField(15);
    JPanel costPanel = new JPanel();

    JLabel namePromptLabel = new JLabel("Enter the name of this expense.");
    JTextField t1 = new JTextField(15);
    JPanel namePanel = new JPanel();

    JLabel paidPromptLabel = new JLabel("Who paid for this expense?");
    ButtonGroup bg = new ButtonGroup();
    ArrayList<JRadioButton> rbutts = new ArrayList<>();
    JPanel paidPanel = new JPanel();

    JLabel sharedPromptLabel = new JLabel("Who shares the cost of this expense?");
    ArrayList<JCheckBox> cboxes = new ArrayList<>();
    JPanel sharedPanel = new JPanel();

    JButton butt = new JButton("OK");
    JPanel closePanel = new JPanel();
    Event event;

    public AddExpense(Event event) {
        setVisible(true);
        setSize(400, 200);
        setLayout(new GridLayout(5, 1)); //default CardLayout. FlowLayout, GridLayout, Null
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.event = event;

        costPanel.add(costPromptLabel);
        costPanel.add(t0);
        add(costPanel);

        namePanel.add(namePromptLabel);
        namePanel.add(t1);
        add(namePanel);

//        BoxLayout layout = new BoxLayout(paidPanel, BoxLayout.Y_AXIS);
//        paidPanel.setLayout(layout);
        paidPanel.add(paidPromptLabel);
        for (Person p : event.getPeople()) {
            JRadioButton b = new JRadioButton(p.getName());
            bg.add(b);
            rbutts.add(b);
            paidPanel.add(b);
        }
        add(paidPanel);

//        BoxLayout layout2 = new BoxLayout(sharedPanel, BoxLayout.Y_AXIS);
//        sharedPanel.setLayout(layout2);
        sharedPanel.add(sharedPromptLabel);
        for (Person p : event.getPeople()) {
            JCheckBox b = new JCheckBox(p.getName());
            cboxes.add(b);
            sharedPanel.add(b);
        }
        add(sharedPanel);

        closePanel.add(butt);
        add(closePanel);

        pack();

        butt.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        double amount = Double.parseDouble(t0.getText());
        String name = t1.getText();

        Person paidBy = this.event.getPeople().get(0);
        for (int i = 0; i < rbutts.size(); i++) {
            if (rbutts.get(i).isSelected()) {
                paidBy = this.event.getPeople().get(i);
            }
        }

        ArrayList<Person> sharedBy = new ArrayList<>();
        for (int i = 0; i < cboxes.size(); i++) {
            if (cboxes.get(i).isSelected()) {
                sharedBy.add(this.event.getPeople().get(i));
            }
        }
        Expense exp1 = new Expense(name, amount, paidBy, sharedBy);
        this.event.addExpense(exp1);
        this.event.reCalculateBalance();
        dispose();
    }
}
