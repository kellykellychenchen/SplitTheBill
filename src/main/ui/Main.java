package ui;

import java.io.FileNotFoundException;

//Main. Run the program from here.
public class Main {
    public static void main(String[] args) {
        try {
            new SplitApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}

