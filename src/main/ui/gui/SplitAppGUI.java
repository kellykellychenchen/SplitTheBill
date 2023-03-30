package ui.gui;

import model.BillBook;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SplitAppGUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/billbook.json";
    private BillBook billBook;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private List<JButton> buttons;
    private List<JLabel> labels;
    public SplitAppGUI() {
        super("Split The Bill!");

        billBook = new BillBook("Kelly's Bill Book");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        buttons = new ArrayList<>();
        labels = new ArrayList<>();

        setVisible(true);
        setSize(400,400);
        setLayout(new FlowLayout()); //default CardLayout. FlowLayout, GridLayout, Null
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel l0 = new JLabel("How would you like to start?");
        labels.add(l0);
        JButton b0 = new JButton("Create a new event");
        buttons.add(b0);
        JButton b1 = new JButton("Select from existing events");
        buttons.add(b1);
        JButton b2 = new JButton("Save billbook to file");
        buttons.add(b2);
        JButton b3 = new JButton("Load billbook from file");
        buttons.add(b3);
        JButton b4 = new JButton("quit");
        buttons.add(b4);
        JLabel l1 = new JLabel();
        labels.add(l1);

        add(l0);
        add(b0);
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(l1);

        b0.addActionListener(this);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttons.get(0)) {
            new CreateEvent(this.billBook);
        }
        if (e.getSource() == buttons.get(1)) {
            new SelectEvent(this.billBook);
        }
        if (e.getSource() == buttons.get(2)) {
            saveBillBook();
        }
        if (e.getSource() == buttons.get(3)) {
            loadBillBook();
        }
        if (e.getSource() == buttons.get(4)) {
            new PromptSave(this);
            dispose();
        }
    }

    public void saveBillBook() {
        try {
            jsonWriter.open();
            jsonWriter.write(billBook);
            jsonWriter.close();
            labels.get(1).setText("Saved " + billBook.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            labels.get(1).setText("Unable to write to file: " + JSON_STORE);
        }
    }

    private void loadBillBook() {
        try {
            this.billBook = jsonReader.read();
            labels.get(1).setText("Loaded " + billBook.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            labels.get(1).setText("Unable to read from file: " + JSON_STORE);
        }
    }


}
