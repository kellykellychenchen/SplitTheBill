package ui.gui.eventmenu;

import model.Event;
import model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Pop-up window for creating a new person.
public class AddPerson extends JFrame implements ActionListener {
    JTextField t1 = new JTextField(15);
    Event event;

    // EFFECTS: constructs a window for adding a new Person inside the given event.
    public AddPerson(Event event) {
        setVisible(true);
        setSize(400, 200);
        setLayout(new FlowLayout()); //default CardLayout. FlowLayout, GridLayout, Null
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.event = event;

        JLabel l1 = new JLabel("Enter the person's name then press ADD.");
        JButton butt = new JButton("ADD");

        add(l1);
        add(t1);
        add(butt);
        butt.addActionListener(this);
    }

    // MODIFIES: Event this.event
    // EFFECTS: When button is pressed, create a new Person with the name that user entered, add it to this.event,
    // and close the current window.
    @Override
    public void actionPerformed(ActionEvent e) {
        String name = t1.getText();
        Person p1 = new Person(name);
        this.event.addPerson(p1);
        dispose();
    }
}
