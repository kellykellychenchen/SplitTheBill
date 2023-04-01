package ui.gui.event_menu;

import model.Expense;
import model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShowExpenses extends JFrame implements ActionListener {
    JLabel l1 = new JLabel("This is a list of expenses currently in this event.");
    DefaultListModel<String> model = new DefaultListModel<>();
    JList jl = new JList(model);
    JScrollPane sp = new JScrollPane(jl);
    JButton butt = new JButton("CLOSE");
    ArrayList<Expense> expenses;
    JPanel ptop = new JPanel();
    JPanel pmid = new JPanel();
    JPanel pbot = new JPanel();

    public ShowExpenses(ArrayList<Expense> expenses) {
        setVisible(true);
        setSize(400, 200);
        setLayout(new FlowLayout()); //default CardLayout. FlowLayout, GridLayout, Null
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.expenses = expenses;

        ptop.add(l1);
        for (Expense exp : this.expenses) {
            model.addElement(exp.getExpenseName());
        }
        jl.setVisibleRowCount(5);
        jl.setFixedCellWidth(200);
        pmid.add(sp);
        pbot.add(butt);

        add(ptop);
        add(pmid);
        add(pbot);

        butt.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
    }
}
