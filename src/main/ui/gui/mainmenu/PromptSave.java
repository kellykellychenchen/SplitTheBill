package ui.gui.mainmenu;

import ui.gui.SplitAppGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Pop-up window to prompt the user to save files before exiting.
public class PromptSave extends JFrame implements ActionListener {
    JButton b1 = new JButton("Yes");
    JButton b2 = new JButton("No");
    SplitAppGUI app;

    // EFFECTS: constructs a window that allows the user to choose if they would like to save the current file.
    public PromptSave(SplitAppGUI app) {
        setVisible(true);
        setSize(400, 100);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.app = app;

        JLabel l1 = new JLabel("Would you like to save your data?");

        add(l1);
        add(b1);
        add(b2);
        b1.addActionListener(this);
        b2.addActionListener(this);
    }

    // EFFECTS: when "Yes" is pressed, save the current file. When "No" is pressed, exit the program.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            this.app.saveBillBook();
        }
        dispose();
        app.dispose();
    }
}
