package ui.gui.mainmenu;

import model.BillBook;
import model.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateEvent extends JFrame implements ActionListener {
    JLabel l1 = new JLabel("Enter the name of your new event and click OK.");
    JTextField t1 = new JTextField(15);
    JButton butt = new JButton("OK");
    BillBook bb;


    public CreateEvent(BillBook billbook) {
        setVisible(true);
        setSize(400, 200);
        setLayout(new FlowLayout()); //default CardLayout. FlowLayout, GridLayout, Null
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.bb = billbook;

        add(l1);
        add(t1);
        add(butt);
        butt.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String eventName = t1.getText();
        Event event = new Event(eventName);
        bb.addEvent(event);
        dispose();
        //System.out.println(bb.getEvents().get(0).getEventName());
    }

}
