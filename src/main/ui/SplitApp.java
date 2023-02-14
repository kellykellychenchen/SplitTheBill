package ui;

import ui.exceptions.InvalidSelectionException;
import ui.exceptions.PersonNotFoundException;
import model.Event;
import model.Expense;
import model.Person;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.Math.abs;

public class SplitApp {
    private ArrayList<Event> events;
    private Scanner input;

    public SplitApp() {
        runSplitter();
    }

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
        System.out.println("Remember to pay your debts. GOODBYE");
    }

    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tc -> Create a new event");
        System.out.println("\ts -> Select from existing events");
        System.out.println("\tq -> quit");
    }

    private void processCommand(String command) {
        if (command.equals("c")) {
            createEvent();
        } else if (command.equals("s")) {
            if (events.isEmpty()) {
                System.out.println("There are no existing events to choose from. You can add one now.");
                createEvent();
            } else {
                chooseEvent();
            }
        } else {
            System.out.println("No. Pick a letter from the list I gave you. It's not that hard. Try again.");
        }
    }

    private void chooseEvent() {
        int i = 0;
        System.out.println("Choose from one of the following existing events.");
        for (Event e : events) {
            System.out.println("\t" + i + " -> " + e.getEventName());
            i++;
        }
        try {
            chooseFromListOfEvents();
        } catch (InvalidSelectionException e) {
            System.out.println("No. Pick from one of the numbers I gave you. Can you even count? Try again.");
            chooseEvent();
        } catch (InputMismatchException e) {
            System.out.println("No. By numbers I mean stuff like 0, 1, 2 ... I thought that was obvious. Try again.");
            input.next();
            chooseEvent();
        }
    }

    private void chooseFromListOfEvents() throws InvalidSelectionException {
        int selection = input.nextInt();
        if (selection < 0 || selection >= events.size()) {
            throw new InvalidSelectionException();
        }
        Event e = events.get(selection);
        System.out.println("You have selected the " + e.getEventName() + " event.");
        modifyEvent(e);
    }

    private void createEvent() {
        System.out.println("What's the name of your new event?");
        String eventName = input.next();
        Event e = new Event(eventName);
        events.add(e);
        System.out.println("A new event " + e.getEventName() + " has been created.");
        modifyEvent(e);
    }

    // ----- EVENT MENU ----- //
    private void modifyEvent(Event e) {
        eventMenu();
        String eventCommand = input.next().toLowerCase();

        if (eventCommand.equals("p")) {
            addPerson(e);
        } else if (eventCommand.equals("vp")) {
            showPeople(e);
        } else if (eventCommand.equals("e")) {
            try {
                addExpense(e);
            } catch (PersonNotFoundException ex) {
                fixPersonNotFound(e);
            }
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
        }
    }


    private void eventMenu() {
        System.out.println("\nWhat would you like to do in this event?");
        System.out.println("\tp -> Add a new person to this event");
        System.out.println("\tvp -> View the list of people in this event");
        System.out.println("\te -> Add a new expense to this event");
        System.out.println("\tve -> View the list of expenses in this event");
        System.out.println("\tt -> View the total cost in this event");
        System.out.println("\ta -> View the amount paid by each person in this event");
        System.out.println("\ts -> View the cost to be shared by each person in this event");
        System.out.println("\to -> View the outstanding balance for each person in this event");
        System.out.println("Press anything else to go back to the main menu.");
    }

    private void addPerson(Event e) {
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
        modifyEvent(e);
    }


    private void showPeople(Event e) {
        if (e.getPeople().size() <= 0) {
            noPeople(e);
        } else {
            System.out.println("Here's the list of people currently in this event:");
            for (Person p : e.getPeople()) {
                System.out.println("\t" + p.getName());
            }
            backToEventMenu(e);
        }
    }


    // add expense method assumes payer & sharers have already been added to people list.
    private void addExpense(Event e) throws PersonNotFoundException {
        if (e.getPeople().size() <= 0) {
            noPeople(e);
        } else {
            Person paidBy = promptPaidBy(e);
            ArrayList<Person> sharedBy = promptSharedBy(e);
            String expNam = promptExpenseName();
            int amount = promptExpenseAmount();

            Expense e1 = new Expense(expNam, amount, paidBy, sharedBy);
            e.addExpense(e1);
            System.out.printf("A new expense of $" + e1.getAmount() + " has been added to this event.");
            backToEventMenu(e);
        }
    }


    private Person promptPaidBy(Event e) throws PersonNotFoundException {
        System.out.println("Who paid for this expense?");
        String name = input.next();
        return findPersonWithName(name, e.getPeople());
    }

    private ArrayList<Person> promptSharedBy(Event e) throws PersonNotFoundException {
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

    private int promptExpenseAmount() {
        System.out.println("How much did this expense cost?");
        int amount = input.nextInt();
        if (amount >= 100 && amount < 1000) {
            System.out.println("wow that's expensive..");
        } else if (amount >= 1000 && amount < 10000) {
            System.out.println("wow that's REALLY expensive..");
        } else if (amount >= 10000) {
            System.out.println("WOW u must be rich... can i be friends with u pls? jk..");
        }
        return amount;
    }
    private String promptExpenseName() {
        System.out.println("Give this expense a name.");
        return input.next();
    }

     private void showExpense(Event e) {
        if (e.getExpenses().size() <= 0) {
            System.out.println("There are no expenses in this event");
            backToEventMenu(e);
        } else {
            System.out.println("Here's the list of expenses currently in this event:");
            int i = 0;
            for (Expense ex : e.getExpenses()) {
                System.out.println("\t" + i + " -> " + ex.getExpenseName() + ": $" + ex.getAmount() + " paid by "
                        + ex.getPaidBy().getName());
                i++;
            }
            System.out.println("Select one of the events to modify or press b to go back to the event.");
            String selection = input.next();
            if (selection.equals("b")) {
                modifyEvent(e);
            } else {
                try {
                    selectExpense(e, selection);
                } catch (InvalidSelectionException exc) {
                    System.out.println("No. Pick from one of the numbers I gave you... It's not that hard.");
                    showExpense(e);
                } catch (NumberFormatException exc) {
                    System.out.println("No. By numbers I mean stuff like 0, 1, 2 ... Try again");
                    input.next();
                    showExpense(e);
                }
            }
        }
    }

    private void selectExpense(Event e, String selection) throws InvalidSelectionException {
        int s = Integer.parseInt(selection);
        if (s < 0 || s >= e.getExpenses().size()) {
            throw new InvalidSelectionException();
        } else {
            Expense ex = e.getExpenses().get(s);
            System.out.println("You have selected " + ex.getExpenseName() + ": $" + ex.getAmount() + " paid by "
                    + ex.getPaidBy().getName());
            modifyExpense(e, ex);
        }
    }

    private void showTotal(Event e) {
        System.out.println("The total cost of all expenses in this event is $" + e.calcTotalCost());
        backToEventMenu(e);
    }

    private void showPaidBy(Event e) {
        if (e.getPeople().size() <= 0) {
            noPeople(e);
        } else {
            for (Person p : e.getPeople()) {
                System.out.println(p.getName() + " has paid a total of $" + e.calcTotalPaidByPerson(p)
                        + " in this event.");
            }
            backToEventMenu(e);
        }
    }

    private void showSharedBy(Event e) {
        if (e.getPeople().size() <= 0) {
            noPeople(e);
        } else {
            for (Person p : e.getPeople()) {
                System.out.println(p.getName() + " shares a total of $" + e.calcTotalSharedByPerson(p)
                        + " in this event.");
            }
            backToEventMenu(e);
        }
    }

    private void showBalance(Event e) {
        if (e.getPeople().size() <= 0) {
            noPeople(e);
        } else {
            for (Person p : e.getPeople()) {
                int balance = e.calcBalance(p);
                if (balance <= 0) {
                    balance = abs(balance);
                    System.out.println(p.getName() + " owes $" + balance);
                } else {
                    System.out.println(p.getName() + " should receive $" + balance);
                }
            }
            backToEventMenu(e);
        }
    }


    // ------ EXPENSE MENU ------ //
    private void modifyExpense(Event e, Expense ex) {
        expenseMenu();
        String expenseCommand = input.next().toLowerCase();

        if (expenseCommand.equals("n")) {
            changeExpenseName(e, ex);
        } else if (expenseCommand.equals("v")) {
            changeExpenseValue(e, ex);
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
        } else if (expenseCommand.equals("b")) {
            modifyEvent(e);
        } else {
            invalidCommand();
        }
    }

    private void expenseMenu() {
        System.out.println("\nWhat would you like to do to this expense?");
        System.out.println("\tn -> Modify its name");
        System.out.println("\tv -> Modify its value");
        System.out.println("\tp -> Change who paid for it");
        System.out.println("\ts -> Change who shares this cost");
        System.out.println("Press b to go back to the event menu.");
    }

    private void changeExpenseName(Event e, Expense ex) {
        System.out.println("Type the new name for this expense.");
        String name = input.next();
        ex.setExpenseName(name);
        System.out.println("The updated name for this expense is " + ex.getExpenseName());
        backToExpenseMenu(e, ex);
    }

    private void changeExpenseValue(Event e, Expense ex) {
        System.out.println("Enter the new value for this expense.");
        int amount = input.nextInt();
        ex.setAmount(amount);
        System.out.println("The updated value for this expense is $" + ex.getAmount());
        backToExpenseMenu(e, ex);
    }

    private void changeExpensePaidBy(Event e, Expense ex) throws PersonNotFoundException {
        System.out.println("Enter the name of the person who paid for this expense.");
        String name = input.next();
        ex.setPaidBy(findPersonWithName(name, e.getPeople()));
        System.out.println("This expense was paid by " + ex.getPaidBy().getName());
        backToExpenseMenu(e, ex);
    }

    private void changeExpenseSharedBy(Event e, Expense ex) throws PersonNotFoundException {
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
        backToExpenseMenu(e, ex);
    }


    // ------ HELPER FUNCTIONS ------//
    private Person findPersonWithName(String name, ArrayList<Person> people) throws PersonNotFoundException {
        for (Person p : people) {
            if (name.equals(p.getName())) {
                return p;
            }
        }
        throw new PersonNotFoundException();
    }

    private void noPeople(Event e) {
        System.out.println("You don't have any people in this event. "
                + "You MUST add people before you can do anything else.");
        forceAddPerson(e);
    }
    private void fixPersonNotFound(Event e) {
        System.out.println("Cannot find a person with this name in the current event. "
                + "You MUST create the person before adding them to any expenses");
        forceAddPerson(e);
    }

    private void forceAddPerson(Event e) {
        System.out.println("You can do one of the following:");
        System.out.println("\ta -> add a person now.");
        System.out.println("\tb -> go back to the event menu.");
        String decision = input.next();
        if (decision.equals("a")) {
            addPerson(e);
        } else if (decision.equals("b")) {
            modifyEvent(e);
        }
    }

    private void invalidCommand() {
        System.out.println("That was not one of the many options I've given you.");
        System.out.println("You obviously cannot follow instructions... "
                + "So you get taken back to the main screen.");
    }

    private void backToEventMenu(Event e) {
        System.out.println("Press any key to go back to the event menu.");
        input.next();
        modifyEvent(e);
    }

    private void backToExpenseMenu(Event e, Expense ex) {
        System.out.println("Press any key to go back to the expense menu.");
        input.next();
        modifyExpense(e, ex);
    }

    private void initialize() {
        events = new ArrayList<>();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

}
