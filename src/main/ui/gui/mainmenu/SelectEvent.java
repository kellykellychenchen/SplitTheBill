package ui.gui.mainmenu;

import model.BillBook;
import model.Event;
import ui.gui.eventmenu.EventMenu;

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
        setSize(400, 200);
        setLayout(new FlowLayout()); //default CardLayout. FlowLayout, GridLayout, Null
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.bb = billbook;
        this.butts = new ArrayList<>();

        add(l1);
        if (bb.getEvents().size() == 0) {
            l1.setText("You haven't created any events in this billbook!! ");
        }
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
