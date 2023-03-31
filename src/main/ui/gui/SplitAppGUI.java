package ui.gui;

import model.BillBook;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.gui.main_menu.CreateEvent;
import ui.gui.main_menu.PromptSave;
import ui.gui.main_menu.SelectEvent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SplitAppGUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/billbook.json";
    private static final String IMG_STORE = "./data/dollar.jpg";
    private BillBook billBook;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private List<JButton> buttons;
    private List<JLabel> labels;
    private JPanel panel;

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

        setUpComponents();
        setUpImage();
        addListeners();
    }

    private void setUpComponents() {
        JLabel l0 = new JLabel("How would you like to start?");
        JButton b0 = new JButton("Create a new event");
        JButton b1 = new JButton("Select from existing events");
        JButton b2 = new JButton("Save billbook to file");
        JButton b3 = new JButton("Load billbook from file");
        JButton b4 = new JButton("quit");
        JLabel l1 = new JLabel();
        labels.add(l0);
        buttons.add(b0);
        buttons.add(b1);
        buttons.add(b2);
        buttons.add(b3);
        buttons.add(b4);
        labels.add(l1);
        add(l0);
        add(b0);
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(l1);
    }

//    private void addComponentsToFrame() {
//        add(labels.get(0));
//        add(buttons.get(0));
//        add(buttons.get(1));
//        add(buttons.get(2));
//        add(buttons.get(3));
//        add(buttons.get(4));
//        add(labels.get(1));
//        add(panel);
//    }

    private void addListeners() {
        for (JButton b : buttons) {
            b.addActionListener(this);
        }
    }

    private void setUpImage() {
        JLabel l2 = new JLabel();
        labels.add(l2);
        panel = new JPanel();
        panel.setSize(200,200);
        panel.add(l2);

        BufferedImage myPicture;
        try {
            myPicture = ImageIO.read(new File(IMG_STORE));
            Image smallPicture = myPicture.getScaledInstance(200,200,
                    Image.SCALE_SMOOTH);
            l2.setIcon(new ImageIcon(smallPicture));
        } catch (IOException e) {
            l2.setText("(failed to load icon. Imagine a cute dollar sign at this place)");
        }
        add(panel);
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
