package ui.gui.mainmenu;

import model.BillBook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Pop-up window for deleting one or all events.
public class DeleteEvent extends JFrame implements ActionListener {
    List<JButton> butts;
    JButton clearButton;
    BillBook bb;

    // EFFECTS: constructs a window with buttons for each event in the given billbook
    // and a button for clearing all events.
    public DeleteEvent(BillBook billbook) {
        setVisible(true);
        setSize(600, 200);
        setLayout(new GridLayout(3,1));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.bb = billbook;
        this.butts = new ArrayList<>();

        setUpComponents();
    }

    // MODIFIES: this.
    // EFFECTS: sets up the labels and buttons for the delete event screen. Stores the buttons as fields.
    private void setUpComponents() {
        JPanel topPanel = new JPanel();
        JPanel midPanel = new JPanel();
        JPanel botPanel = new JPanel();
        add(topPanel);
        add(midPanel);
        add(botPanel);

        JLabel l1 = new JLabel("Select one of the following existing events to DELETE it from this billbook.");
        topPanel.add(l1);
        if (bb.getEvents().size() == 0) {
            l1.setText("You don't have any events in this billbook!! ");
        } else {
            for (int i = 0; i < bb.getEvents().size(); i++) {
                JButton b = new JButton(bb.getEvents().get(i).getEventName());
                topPanel.add(b);
                butts.add(b);
                b.addActionListener(this);
            }

            JLabel l2 = new JLabel("Alternatively, you can reset the billbook by CLEARING ALL events.");
            midPanel.add(l2);
            clearButton = new JButton("RESET BILLBOOK");
            botPanel.add(clearButton);
            clearButton.addActionListener(this);
        }
    }

    // MODIFIES: Billbook this.bb
    // EFFECTS: Remove the selected event from this.billbook or remove all events from this.billbook depending on
    // which button is pressed. Dispose window after one action is performed.
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < this.butts.size(); i++) {
            if (e.getSource() == this.butts.get(i)) {
                bb.removeEvent(bb.getEvents().get(i));
                dispose();
            }
        }
        if (e.getSource() == clearButton) {
            bb.clearEvents();
            dispose();
        }
    }
}
