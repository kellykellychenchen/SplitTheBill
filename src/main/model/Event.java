package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Event class represents an event with an event name, a list of people that participated in the event, and list of
// expenses associated with the event.
public class Event implements Writable {
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
    // EFFECTS: resets the name of this event with the given name.
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    // MODIFIES: this and person p.
    // EFFECTS: adds a given person to this event's people list, and adds this event to the given person's events list.
    public void addPerson(Person p) {
        people.add(p);
        p.addEvent(this);
    }

    // MODIFIES: this and expense ex.
    // EFFECTS: adds a given expense to the event's expense list, and sets the given expense's fromEvent to this event.
    public void addExpense(Expense ex) {
        expenses.add(ex);
        ex.setFromEvent(this);
    }

    // EFFECTS: returns a number representing the total cost of all expenses in this event.
    public double calcTotalCost() {
        double result = 0;
        for (Expense e : this.expenses) {
            result += e.getAmount();
        }
        return result;
    }

    // EFFECTS: returns a number representing the total amount of all expense paid by the given person in this event.
    public double calcTotalPaidByPerson(Person p) {
        double result = 0;
        for (Expense e : expenses) {
            if (e.getPaidBy() == p) {
                result += e.getAmount();
            }
        }
        return result;
    }

    // EFFECTS: returns a number representing the total amount of shared costs shared by the given person in this event.
    public double calcTotalSharedByPerson(Person p) {
        double result = 0;
        for (Expense e : expenses) {
            if (e.getSharedBy().contains(p)) {
                result += e.splitAmount();
            }
        }
        return result;
    }

    // EFFECTS: returns a number representing the total balance of the given person in this event. A positive number
    // means they should receive this amount from others. A negative number means the amount they owe to others.
    public double calcBalance(Person p) {
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

    // TODO: document
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", eventName);
        json.put("people", peopleToJson());
        json.put("expenses", expensesToJson());
        return json;
    }

    private JSONArray peopleToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Person person : people) {
            jsonArray.put(person.toJson());
        }
        return jsonArray;
    }

    private JSONArray expensesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Expense expense : expenses) {
            jsonArray.put(expense.toJson());
        }
        return jsonArray;
    }







}
