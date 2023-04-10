package ui.gui.mainmenu;

import model.BillBook;
import model.SpendingEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Pop-up window for creating a new event.
public class CreateEvent extends JFrame implements ActionListener {
    JTextField t1 = new JTextField(15);
    BillBook bb;

    // EFFECTS: constructs a window for creating new events inside the given billbook.
    public CreateEvent(BillBook billbook) {
        setVisible(true);
        setSize(400, 200);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.bb = billbook;

        JLabel l1 = new JLabel("Enter the name of your new event and click OK.");
        JButton butt = new JButton("OK");

        add(l1);
        add(t1);
        add(butt);
        butt.addActionListener(this);
    }

    // MODIFIES: Billbook this.bb
    // EFFECTS: When button is pressed, create a new event with the name that user entered, add it to this.billbook,
    // and close the current window.
    @Override
    public void actionPerformed(ActionEvent e) {
        String eventName = t1.getText();
        SpendingEvent spendingEvent = new SpendingEvent(eventName);
        bb.addEvent(spendingEvent);
        dispose();
    }

}
