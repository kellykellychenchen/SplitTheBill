package ui.gui;

import javax.swing.*;
import java.awt.*;

public class SplitAppGUI extends JFrame {
    public SplitAppGUI() {
        super("Split The Bill!");
        setVisible(true);
        setSize(400,400);
        setLayout(new FlowLayout()); //default CardLayout. FlowLayout, GridLayout, Null
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel l = new JLabel("Hello!!");
        JLabel l1 = new JLabel("Welcome here");
        add(l);
        add(l1);

        Addition obj = new Addition();
    }
}
