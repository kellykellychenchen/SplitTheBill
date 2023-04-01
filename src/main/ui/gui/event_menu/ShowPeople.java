package ui.gui.event_menu;

import model.Event;
import model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShowPeople extends JFrame implements ActionListener {
    JLabel l1 = new JLabel("This is a list of people currently in this event.");
    DefaultListModel<String> model = new DefaultListModel<>();
    JList jl = new JList(model);
    JScrollPane sp = new JScrollPane(jl);
    JButton butt = new JButton("CLOSE");
    ArrayList<Person> people;
    JPanel ptop = new JPanel();
    JPanel pmid = new JPanel();
    JPanel pbot = new JPanel();

    public ShowPeople(ArrayList<Person> people) {
        setVisible(true);
        setSize(400, 200);
        setLayout(new FlowLayout()); //default CardLayout. FlowLayout, GridLayout, Null
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.people = people;

        add(l1);
        for (Person p : this.people) {
            model.addElement(p.getName());
        }
        jl.setVisibleRowCount(5);
        jl.setFixedCellWidth(200);

        add(sp);
        add(butt);

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
