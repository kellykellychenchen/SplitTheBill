package model;

import java.util.ArrayList;

// Event class represents an event with an event name, a list of people that participated in the event, and list of
// expenses associated with the event.
public class Event {
    private String eventName;
    private ArrayList<Person> people;
    private ArrayList<Expense> expenses;

    // EFFECTS: constructs an event with a given event name, an empty list of people, and an empty list of expenses.
    public Event(String eventName) {
        this.eventName = eventName;
        this.people = new ArrayList<>();
        this.expenses = new ArrayList<>();
    }

    // MODIFIES: this.
    // EFFECTS: re-names an event with the given name.
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    // MODIFIES: this and person p.
    // EFFECTS: adds a given person to the event's people list. also adds the current event to that person's event list.
    public void addPerson(Person p) {
        people.add(p);
        p.addEvent(this);
    }

    // MODIFIES: this.
    // EFFECTS: adds a given expense to the event's expense list.
    public void addExpense(Expense e) {
        expenses.add(e);
    }

    // EFFECTS: returns an integer representing the total cost of all expenses in this event.
    public int calcTotalCost() {
        int result = 0;
        for (Expense e : this.expenses) {
            result += e.getAmount();
        }
        return result;
    }

    // EFFECTS: takes a person p and returns an integer representing the total amount of all expense that were paid by
    // the given person in this event.
    public int calcTotalPaidByPerson(Person p) {
        int result = 0;
        for (Expense e : expenses) {
            if (e.getPaidBy() == p) {
                result += e.getAmount();
            }
        }
        return result;
    }

    // EFFECTS: takes a person p and returns an integer representing the total amount of shared costs that are shared by
    // the given person in this event.
    public int calcTotalSharedByPerson(Person p) {
        int result = 0;
        for (Expense e : expenses) {
            if (e.getSharedBy().contains(p)) {
                result += e.splitAmount();
            }
        }
        return result;
    }

    // EFFECTS: takes a person p and returns an integer representing the total balance of the given person in this
    // event. A positive amount indicates the person has paid more than his share and is entitled to receive this
    // amount from other people in this event. A negative amount means this person has paid for less than his share and
    // owes that amount to other people in this event.
    public int calcBalance(Person p) {
        return calcTotalPaidByPerson(p) - calcTotalSharedByPerson(p);
    }

    // EFFECTS: returns a string representing the event's name.
    public String getEventName() {
        return eventName;
    }

    // EFFECTS: returns an array list representing the list of people associated with this event.
    public ArrayList<Person> getPeople() {
        return people;
    }

    // EFFECTS: returns an array list representing the list of expenses associated with this event.
    public ArrayList<Expense> getExpenses() {
        return expenses;
    }

}
