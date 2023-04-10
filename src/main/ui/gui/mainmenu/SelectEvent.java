package ui.gui.mainmenu;

import model.BillBook;
import ui.gui.eventmenu.EventMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Pop-up window for selecting an existing event.
public class SelectEvent extends JFrame implements ActionListener {
    List<JButton> butts;
    BillBook bb;

    // EFFECTS: constructs a window with buttons for each event in the given billbook.
    public SelectEvent(BillBook billbook) {
        setVisible(true);
        setSize(400, 200);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.bb = billbook;
        this.butts = new ArrayList<>();

        JLabel l1 = new JLabel("Select from one of the following existing events to open it.");

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

    // EFFECTS: When a button is pressed, create a new event menu pop-up window for the selected event and close the
    // current window.
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < this.butts.size(); i++) {
            if (e.getSource() == this.butts.get(i)) {
                new EventMenu(bb.getEvents().get(i));
                dispose();
            }
        }
    }
}
