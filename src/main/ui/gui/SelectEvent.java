package ui.gui;

import model.BillBook;
import model.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SelectEvent extends JFrame implements ActionListener {
    JLabel l1 = new JLabel("Select from one of the following existing events.");
    BillBook bb;
    List<JButton> butts;
    Event selectedEvent;

    public SelectEvent(BillBook billbook) {
        setVisible(true);
        setSize(400, 400);
        setLayout(new FlowLayout()); //default CardLayout. FlowLayout, GridLayout, Null
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.bb = billbook;
        this.butts = new ArrayList<>();

        add(l1);
        for (int i = 0; i < bb.getEvents().size(); i++) {
            JButton b = new JButton(bb.getEvents().get(i).getEventName());
            add(b);
            butts.add(b);
            b.addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < this.butts.size(); i++) {
            if (e.getSource() == this.butts.get(i)) {
                selectedEvent = bb.getEvents().get(i);
                new EventMenu(selectedEvent);
                dispose();
            }
        }
    }
}
