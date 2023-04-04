package ui.gui;

import model.BillBook;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.gui.mainmenu.CreateEvent;
import ui.gui.mainmenu.DeleteEvent;
import ui.gui.mainmenu.PromptSave;
import ui.gui.mainmenu.SelectEvent;

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

// Main menu window for the Split The Bill application.
public class SplitAppGUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/billbook.json";
    private static final String IMG_STORE = "./data/dollar.jpg";
    private BillBook billBook;
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;
    private List<JButton> buttons;
    private JLabel l1;
    private JPanel panel;

    // EFFECTS: constructs an empty billbook and creates the window for the main menu.
    public SplitAppGUI() {
        super("Split The Bill!");

        billBook = new BillBook("Kelly's Bill Book");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        buttons = new ArrayList<>();

        setVisible(true);
        setSize(400,400);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setUpComponents();
        setUpImage();
        addListeners();
    }

    // MODIFIES: this.
    // EFFECTS: sets up the labels and buttons for the main menu and stores them as fields.
    private void setUpComponents() {
        JLabel l0 = new JLabel("How would you like to start?");
        JButton b0 = new JButton("Create a new event");
        JButton b1 = new JButton("Open an event");
        JButton b2 = new JButton("Save billbook");
        JButton b3 = new JButton("Load billbook from file");
        JButton b4 = new JButton("quit");
        JButton b5 = new JButton("Remove an event");

        l1 = new JLabel();
        buttons.add(b0);
        buttons.add(b1);
        buttons.add(b2);
        buttons.add(b3);
        buttons.add(b4);
        buttons.add(b5);
        add(l0);
        add(b0);
        add(b1);
        add(b5);
        add(b2);
        add(b3);
        add(b4);
        add(l1);
    }

    // EFFECTS: adds this as listeners for all buttons.
    private void addListeners() {
        for (JButton b : buttons) {
            b.addActionListener(this);
        }
    }

    // EFFECTS: sets up the image icon in the main menu.
    private void setUpImage() {
        JLabel l2 = new JLabel();
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
            l2.setText("(failed to load icon. Imagine a dozen cute dollar signs at this location)");
        }
        add(panel);
    }

    // EFFECTS: specifies the actions taken when each button is pressed.
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
        if (e.getSource() == buttons.get(5)) {
            new DeleteEvent(this.billBook);
        }
    }

    // EFFECTS: saves billbook to file
    public void saveBillBook() {
        try {
            jsonWriter.open();
            jsonWriter.write(billBook);
            jsonWriter.close();
            l1.setText("Saved " + billBook.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            l1.setText("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads billbook from file and assigns it to this.billBook.
    private void loadBillBook() {
        try {
            this.billBook = jsonReader.read();
            l1.setText("Loaded " + billBook.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            l1.setText("Unable to read from file: " + JSON_STORE);
        }
    }
}
