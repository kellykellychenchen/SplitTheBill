package ui.gui.mainmenu;

import ui.gui.SplitAppGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PromptSave extends JFrame implements ActionListener {
    JLabel l1 = new JLabel("Would you like to save your data?");
    JButton b1 = new JButton("Yes");
    JButton b2 = new JButton("No");
    SplitAppGUI app;

    public PromptSave(SplitAppGUI app) {
        setVisible(true);
        setSize(400, 100);
        setLayout(new FlowLayout()); //default CardLayout. FlowLayout, GridLayout, Null
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.app = app;

        add(l1);
        add(b1);
        add(b2);
        b1.addActionListener(this);
        b2.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            this.app.saveBillBook();
        }
        dispose();
    }
}
