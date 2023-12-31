package ui;

import model.BillBook;
import model.SpendingEvent;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.exceptions.InvalidSelectionException;
import ui.exceptions.PersonNotFoundException;
import model.Expense;
import model.Person;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Math.abs;

// SplitApp includes the console-based user interface for using the Split The Bill application.
public class SplitApp {
    private static final String JSON_STORE = "./data/billbook.json";
    private Scanner input;
    private BillBook billBook;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: constructs billbook and runs the splitter application.
    public SplitApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        billBook = new BillBook("Kelly's Bill Book");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runSplitter();
    }

    // EFFECTS: initializes the application, displays the main menu, and keeps the program running until the command
    // "q" is entered by user. When "q" is received, prompt the user to save data to file.
    private void runSplitter() {
        boolean keepGoing = true;
        String command;
        initialize();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        promptSave();
        System.out.println("Remember to pay your debts. GOODBYE");
    }

    // EFFECTS: prompts the user to save data to file and gives them the option to do so or not.
    private void promptSave() {
        System.out.println("Would you like to save your data? Enter 'y' for yes or 'n' for no.");
        String command = input.next();
        command = command.toLowerCase();
        if (command.equals("y")) {
            saveBillBook();
        }
    }

    // EFFECTS: prints out the options in the main menu.
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tc -> Create a new event");
        System.out.println("\tx -> Select from existing events");
        System.out.println("\ts -> Save billbook to file");
        System.out.println("\tl -> Load billbook from file");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: processes user command from the main menu by creating an event, choosing an existing event, saving the
    // current billbook to file, or loading a billbook from file.
    private void processCommand(String command) {
        if (command.equals("c")) {
            createEvent();
        } else if (command.equals("x")) {
            if (billBook.getEvents().isEmpty()) {
                System.out.println("There are no existing events to choose from. You can add one now.");
                createEvent();
            } else {
                chooseEvent();
            }
        } else if (command.equals("s")) {
            saveBillBook();
        } else if (command.equals("l")) {
            loadBillBook();
        } else {
            youAreWrongTryAgain();
        }
    }

    // EFFECTS: prompts the user for a name, creates a new event with that name, and takes the user to the event menu
    // for that event.
    private void createEvent() {
        System.out.println("What's the name of your new event?");
        String eventName = input.next();
        SpendingEvent e = new SpendingEvent(eventName);
        billBook.addEvent(e);
        System.out.println("A new event " + e.getEventName() + " has been created.");
        runEvent(e);
    }

    // REQUIRES: user must enter an integer.
    // EFFECTS: prints out the list of existing events and prompts the user to select from one of them.
    private void chooseEvent() {
        int i = 0;
        System.out.println("Choose from one of the following existing events. Your selection MUST be an integer or "
                + "the universe will implode.");
        for (SpendingEvent e : billBook.getEvents()) {
            System.out.println("\t" + i + " -> " + e.getEventName());
            i++;
        }
        try {
            int selection = input.nextInt();
            if (selection < 0 || selection >= billBook.getEvents().size()) {
                throw new InvalidSelectionException();
            }
            SpendingEvent e = billBook.getEvents().get(selection);
            System.out.println("You have selected the " + e.getEventName() + " event.");
            runEvent(e);
        } catch (InvalidSelectionException e) {
            youAreWrongTryAgain();
            chooseEvent();
        }
    }


    // ----- EVENT MENU ----- //
    // EFFECTS: runs the event menu and keeps running until the command "b" is entered by user. When "b" is received,
    // take the user back to the main menu.
    private void runEvent(SpendingEvent e) {
        boolean keepGoing = true;
        String command;

        while (keepGoing) {
            eventMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("b")) {
                keepGoing = false;
            } else {
                modifyEvent(e, command);
            }
        }
    }

    // EFFECTS: processes user command from the event menu.
    private void modifyEvent(SpendingEvent e, String eventCommand) {
        if (eventCommand.equals("p")) {
            addPerson(e);
        } else if (eventCommand.equals("e")) {
            try {
                addExpense(e);
            } catch (PersonNotFoundException ex) {
                fixPersonNotFound(e);
            }
        } else if (eventCommand.equals("vp")) {
            showPeople(e);
        } else if (eventCommand.equals("ve")) {
            showExpense(e);
        } else if (eventCommand.equals("t")) {
            showTotal(e);
        } else if (eventCommand.equals("a")) {
            showPaidBy(e);
        } else if (eventCommand.equals("s")) {
            showSharedBy(e);
        } else if (eventCommand.equals("o")) {
            showBalance(e);
        } else {
            youAreWrongTryAgain();
        }
    }

    // EFFECTS: prints out the options for users to choose from in an event menu.
    private void eventMenu() {
        System.out.println("\nWhat would you like to do in this event?");
        System.out.println("\tp -> Add a new person to this event");
        System.out.println("\te -> Add a new expense to this event");
        System.out.println("\tvp -> View the list of people in this event");
        System.out.println("\tve -> View the list of expenses in this event");
        System.out.println("\tt -> View the total cost in this event");
        System.out.println("\ta -> View the amount paid by each person in this event");
        System.out.println("\ts -> View the cost to be shared by each person in this event");
        System.out.println("\to -> View the outstanding balance for each person in this event");
        System.out.println("Enter 'b' to go back to the main menu.");
    }

    // MODIFIES: the given event e.
    // EFFECTS: prompts the user to enter a name, creates a new person with that name, and adds that person to the given
    // event. Continues to prompt for new names until command "n" is entered.
    private void addPerson(SpendingEvent e) {
        System.out.println("What's the name of this person being added?");
        String name = input.next();
        Person p1 = new Person(name);
        e.addPerson(p1);
        System.out.println(name + " has been added to this event. ");

        boolean loop = true;
        while (loop) {
            System.out.println("Enter the name of another person to add or n if no more.");
            name = input.next();
            if (name.equals("n")) {
                loop = false;
            } else {
                Person p = new Person(name);
                e.addPerson(p);
                System.out.println(name + " has been added to this event. ");
            }
        }
    }

    // MODIFIES: the given event e.
    // EFFECTS: prompts the user for information on an expense, creates a new expense with received information, adds
    // the new expense to this event.
    private void addExpense(SpendingEvent e) throws PersonNotFoundException {
        if (e.getPeople().size() <= 0) {
            noPeople(e);
        } else {
            Person paidBy = promptPaidBy(e);
            ArrayList<Person> sharedBy = promptSharedBy(e);
            String expNam = promptExpenseName();
            double amount = promptExpenseAmount();

            Expense e1 = new Expense(expNam, amount, paidBy, sharedBy);
            e.addExpense(e1);
            e.reCalculateBalance();
            System.out.println("A new expense of $" + String.format("%.2f", e1.getAmount())
                    + " has been added to this event.");
            backToPrevMenu();
        }
    }

    // EFFECTS: prompts the user to enter the name of person who paid for an expense and returns that person.
    private Person promptPaidBy(SpendingEvent e) throws PersonNotFoundException {
        System.out.println("Who paid for this expense?");
        String name = input.next();
        return findPersonWithName(name, e.getPeople());
    }

    // EFFECTS: prompts the user to enter names of people who shared an expense and returns a list of those people.
    private ArrayList<Person> promptSharedBy(SpendingEvent e) throws PersonNotFoundException {
        System.out.println("Who should this cost be shared by? Enter their names one at a time.");
        boolean loop = true;
        ArrayList<Person> sharedBy = new ArrayList<>();
        String n1 = input.next();
        sharedBy.add(findPersonWithName(n1, e.getPeople()));
        while (loop) {
            System.out.println("Anyone else? Enter another name or n if no more.");
            String n = input.next();
            if (n.equals("n")) {
                loop = false;
            } else {
                sharedBy.add(findPersonWithName(n, e.getPeople()));
            }
        }
        return sharedBy;
    }

    // REQUIRES: user must enter a number.
    // EFFECTS: prompts the user to enter the amount of an expense and returns a number representing that amount.
    private double promptExpenseAmount() {
        System.out.println("How much did this expense cost? Enter a number.");
        double amount = input.nextDouble();
        if (amount >= 10000) {
            System.out.println("wow that's expensive..");
        }
        return amount;
    }

    // EFFECTS: prompts the user for an expense name and returns a string representing that name.
    private String promptExpenseName() {
        System.out.println("Give this expense a name.");
        return input.next();
    }

    // EFFECTS: prints out the list of people's names in the given event.
    private void showPeople(SpendingEvent e) {
        if (e.getPeople().size() <= 0) {
            noPeople(e);
        } else {
            System.out.println("Here's the list of people currently in this event:");
            for (Person p : e.getPeople()) {
                System.out.println("\t" + p.getName());
            }
            backToPrevMenu();
        }
    }

    // EFFECTS: prints out a list of the expenses in the given event, and prompts the user to select one of the expenses
    // to modify or go back to the event menu.
    private void showExpense(SpendingEvent e) {
        if (e.getExpenses().size() <= 0) {
            System.out.println("There are no expenses in this event");
            backToPrevMenu();
        } else {
            System.out.println("Here's the list of expenses currently in this event:");
            int i = 0;
            for (Expense ex : e.getExpenses()) {
                System.out.println("\t" + i + " -> " + ex.getExpenseName() + ": $"
                        + String.format("%.2f", ex.getAmount()));
                i++;
            }
            System.out.println("Select one of the events to modify or press any other key to go back to the event.");
            String selection = input.next();
            try {
                int s = Integer.parseInt(selection);
                if (s < 0 || s >= e.getExpenses().size()) {
                    throw new InvalidSelectionException();
                } else {
                    selectExpense(e, s);
                }
            } catch (NumberFormatException | InvalidSelectionException exception) {
                // do nothing
            }
        }
    }

    // EFFECTS: prints the name of the expense selected, then takes the user to the expense menu for that expense.
    private void selectExpense(SpendingEvent e, int s) {
        Expense ex = e.getExpenses().get(s);
        System.out.println("You have selected " + ex.getExpenseName() + ": $"
                + String.format("%.2f", ex.getAmount()));
        runExpense(e, ex);
    }

    // EFFECTS: prints out the total cost of the given event.
    private void showTotal(SpendingEvent e) {
        System.out.println("The total cost of all expenses in this event is $"
                + String.format("%.2f", e.calcTotalCost()));
        backToPrevMenu();
    }

    // EFFECTS: prints out the list of all people in the given event with the total amount they have paid for in the
    // given event.
    private void showPaidBy(SpendingEvent e) {
        if (e.getPeople().size() <= 0) {
            noPeople(e);
        } else {
            for (Person p : e.getPeople()) {
                System.out.println(p.getName() + " has paid a total of $"
                        + String.format("%.2f", p.getTotalPaid()) + " in this event.");
            }
            backToPrevMenu();
        }
    }

    // EFFECTS: prints out the list of all people in the given event with the total amount of costs they share in the
    // given event.
    private void showSharedBy(SpendingEvent e) {
        if (e.getPeople().size() <= 0) {
            noPeople(e);
        } else {
            for (Person p : e.getPeople()) {
                System.out.println(p.getName() + " shares a total of $"
                        + String.format("%.2f", p.getTotalShared()) + " in this event.");
            }
            backToPrevMenu();
        }
    }

    // EFFECTS: prints out the list of all people in the given event with the total balance they owe or should receive
    // from all expenses in the given event.
    private void showBalance(SpendingEvent e) {
        if (e.getPeople().size() <= 0) {
            noPeople(e);
        } else {
            for (Person p : e.getPeople()) {
                double balance = p.getBalance();
                if (balance <= 0) {
                    balance = abs(balance);
                    System.out.println(p.getName() + " owes $" + String.format("%.2f", balance));
                } else {
                    System.out.println(p.getName() + " should receive $" + String.format("%.2f", balance));
                }
            }
            backToPrevMenu();
        }
    }


    // ------ EXPENSE MENU ------ //
    // EFFECTS: runs the expense menu and keeps running until the command "b" is entered by user. When "b" is received,
    // take the user back to the event menu.
    private void runExpense(SpendingEvent e, Expense ex) {
        boolean keepGoing = true;
        String command;

        while (keepGoing) {
            expenseMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("b")) {
                keepGoing = false;
            } else {
                modifyExpense(e, ex, command);
            }
        }
    }

    // EFFECTS: processes user command from the expense menu.
    private void modifyExpense(SpendingEvent e, Expense ex, String expenseCommand) {
        if (expenseCommand.equals("n")) {
            changeExpenseName(ex);
        } else if (expenseCommand.equals("v")) {
            changeExpenseValue(ex);
        } else if (expenseCommand.equals("p")) {
            try {
                changeExpensePaidBy(e, ex);
            } catch (PersonNotFoundException exc) {
                fixPersonNotFound(e);
            }
        } else if (expenseCommand.equals("s")) {
            try {
                changeExpenseSharedBy(e, ex);
            } catch (PersonNotFoundException exc) {
                fixPersonNotFound(e);
            }
        } else {
            youAreWrongTryAgain();
        }
    }

    // EFFECTS: prints out the options for users to choose from in the expense menu.
    private void expenseMenu() {
        System.out.println("\nWhat would you like to do to this expense?");
        System.out.println("\tn -> Modify its name");
        System.out.println("\tv -> Modify its value");
        System.out.println("\tp -> Change who paid for it");
        System.out.println("\ts -> Change who shares this cost");
        System.out.println("Enter 'b' to go back to the event menu.");
    }

    // MODIFIES: the given expense ex
    // EFFECTS: prompts the user to enter a new name for this expense, sets the expense name to the new name.
    private void changeExpenseName(Expense ex) {
        System.out.println("Type the new name for this expense.");
        String name = input.next();
        ex.setExpenseName(name);
        System.out.println("The updated name for this expense is " + ex.getExpenseName());
        backToPrevMenu();
    }

    // REQUIRES: user must enter a number.
    // MODIFIES: the given expense ex
    // EFFECTS: prompts the user to enter a new value for this expense, sets the expense amount to the new amount.
    private void changeExpenseValue(Expense ex) {
        System.out.println("Enter the new value for this expense.");
        double amount = input.nextDouble();
        ex.setAmount(amount);
        System.out.println("The updated value for this expense is $" + String.format("%.2f", ex.getAmount()));
        backToPrevMenu();
    }

    // MODIFIES: the given expense ex
    // EFFECTS: prompts the user to enter the name of a person who paid for this expense, sets the expense's paidBy
    // to the person with the given name.
    private void changeExpensePaidBy(SpendingEvent e, Expense ex) throws PersonNotFoundException {
        System.out.println("Enter the name of the person who paid for this expense.");
        String name = input.next();
        ex.setPaidBy(findPersonWithName(name, e.getPeople()));
        System.out.println("This expense was paid by " + ex.getPaidBy().getName());
        backToPrevMenu();
    }

    // MODIFIES: the given expense ex
    // EFFECTS: prompts the user to enter the names of people who paid for this expense, sets the expense's sharedBy
    // to the list of people with the given name.
    private void changeExpenseSharedBy(SpendingEvent e, Expense ex) throws PersonNotFoundException {
        System.out.println("Enter the people that share the cost of this expense. Enter one name at a time.");
        String name = input.next();
        ArrayList<Person> sharedBy = new ArrayList<>();
        sharedBy.add(findPersonWithName(name, e.getPeople()));
        boolean loop = true;
        while (loop) {
            System.out.println("Enter the next person's name or n if no more.");
            String n = input.next();
            if (n.equals("n")) {
                loop = false;
            } else {
                sharedBy.add(findPersonWithName(n, e.getPeople()));
            }
        }
        ex.setSharedBy(sharedBy);
        System.out.println("The people that share this cost has been updated to:");
        for (Person p : ex.getSharedBy()) {
            System.out.println("\t" + p.getName());
        }
        backToPrevMenu();
    }


    // ------ HELPER FUNCTIONS ------//
    // EFFECTS: takes a name and a list of people, and returns the first person found in the list of people with the
    // given name.
    private Person findPersonWithName(String name, ArrayList<Person> people) throws PersonNotFoundException {
        for (Person p : people) {
            if (name.equals(p.getName())) {
                return p;
            }
        }
        throw new PersonNotFoundException();
    }

    // EFFECTS: prints out a statement telling the user that there are currently no people in the given event, then
    // prompts the user to add a person to the given event.
    private void noPeople(SpendingEvent e) {
        System.out.println("You don't have any people in this event. "
                + "You MUST add people before you can do anything else. ");
        promptAddPerson(e);
    }

    // EFFECTS: prints out a statement telling the user that a person with the name they entered does not exist in the
    // given event, then prompts the user to add a person to the given event.
    private void fixPersonNotFound(SpendingEvent e) {
        System.out.println("Cannot find a person with this name in the current event. "
                + "You MUST create the person before adding them to any expenses. ");
        promptAddPerson(e);
    }

    // EFFECTS: request user to choose between adding a person or going back to the event menu, then process the
    // command received.
    private void promptAddPerson(SpendingEvent e) {
        System.out.println("You can do one of the following:");
        System.out.println("\ta -> add a person now.");
        System.out.println("Press enter again to go back to the previous menu.");
        String decision = input.next();
        if (decision.equals("a")) {
            addPerson(e);
        }
    }

    // EFFECTS: takes the user back to the previous menu after any keyboard input.
    private void backToPrevMenu() {
        System.out.println("Press enter again to go back to the previous menu.");
        input.next();
    }

    // EFFECTS: initializes the splitter app with an empty list of events, an input representing the keyboard input,
    // and delimiter "\n".
    private void initialize() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: prints out a statement telling the user that the previous input was invalid.
    private void youAreWrongTryAgain() {
        System.out.println("That's not a valid selection. Pick something from the list I gave you. Try again.");
    }


    // ------ PERSISTENCE ------//
    // EFFECTS: saves billbook to file
    private void saveBillBook() {
        try {
            jsonWriter.open();
            jsonWriter.write(billBook);
            jsonWriter.close();
            System.out.println("Saved " + billBook.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads billbook from file
    private void loadBillBook() {
        try {
            this.billBook = jsonReader.read();
            System.out.println("Loaded " + billBook.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
