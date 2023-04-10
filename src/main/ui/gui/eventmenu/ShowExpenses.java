package ui.gui.eventmenu;

import model.Expense;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Pop-up window showing a list of all expenses.
public class ShowExpenses extends JFrame implements ActionListener {
    ArrayList<Expense> expenses;

    // EFFECTS: constructs a window showing the name of all expenses in the given list of expenses.
    public ShowExpenses(ArrayList<Expense> expenses) {
        setVisible(true);
        setSize(400, 200);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.expenses = expenses;

        setUpComponents();
    }

    // EFFECTS: sets up the labels and buttons for the show expenses screen.
    private void setUpComponents() {
        JPanel topPanel = new JPanel();
        JPanel midPanel = new JPanel();
        JPanel botPanel = new JPanel();

        JLabel l1 = new JLabel("This is a list of expenses currently in this spendingEvent.");
        topPanel.add(l1);

        JScrollPane sp = getjScrollPane();
        midPanel.add(sp);

        JButton butt = new JButton("CLOSE");
        botPanel.add(butt);

        add(topPanel);
        add(midPanel);
        add(botPanel);

        butt.addActionListener(this);
    }

    // EFFECTS: sets up the scrolling section for the middle JPanel.
    private JScrollPane getjScrollPane() {
        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> jl = new JList<String>(model);
        JScrollPane sp = new JScrollPane(jl);
        for (Expense exp : this.expenses) {
            model.addElement(exp.getExpenseName());
        }
        jl.setVisibleRowCount(5);
        jl.setFixedCellWidth(200);
        return sp;
    }

    // EFFECTS: when button is pressed, close the current window.
    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
    }
}
