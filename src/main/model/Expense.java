package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Expense represents an expense which has an expense name, an amount, a person who paid for the expense, and a list of
// people that share the cost of this expense.
public class Expense implements Writable {
    private String expenseName;
    private double amount;
    private Person paidBy;
    private ArrayList<Person> sharedBy;
    private Event fromEvent;

    // EFFECTS: constructs an expense with the given expense name, given amount of the cost, a given Person that paid
    // for this expense, and a given list of people that the cost of this expense is to be shared amongst.
    public Expense(String expenseName, double amount, Person paidBy, ArrayList<Person> sharedBy) {
        this.expenseName = expenseName;
        this.amount = amount;
        this.paidBy = paidBy;
        this.sharedBy = sharedBy;
        this.fromEvent = null;
    }

    // EFFECTS: returns a number representing the shared cost per person by dividing the expense cost evenly amongst
    // the list of people who share this cost.
    public double splitAmount() {
        return amount / sharedBy.size();
    }

    // MODIFIES: this
    // EFFECTS: sets the name of the expense with the given name.
    public void setExpenseName(String expNam) {
        this.expenseName = expNam;
    }

    // MODIFIES: this
    // EFFECTS: sets the expense amount with the given amount.
    public void setAmount(double amount) {
        this.amount = amount;
    }

    // MODIFIES: this
    // EFFECTS: sets the person who paid for this expense with the given person.
    public void setPaidBy(Person paidBy) {
        this.paidBy = paidBy;
    }

    // MODIFIES: this
    // EFFECTS: sets the list of people that share this expense with the new list of people.
    public void setSharedBy(ArrayList<Person> sharedBy) {
        this.sharedBy = sharedBy;
    }

    // MODIFIES: this
    // EFFECTS: sets the event that this expense is from with the given event.
    public void setFromEvent(Event e) {
        this.fromEvent = e;
    }

    // EFFECTS: returns the name of this expense.
    public String getExpenseName() {
        return this.expenseName;
    }

    // EFFECTS: returns the cost of this expense.
    public double getAmount() {
        return this.amount;
    }

    // EFFECTS: returns the person that paid for this expense.
    public Person getPaidBy() {
        return this.paidBy;
    }

    // EFFECTS: returns the list of people that share this expense.
    public ArrayList<Person> getSharedBy() {
        return this.sharedBy;
    }

    // EFFECTS: returns the event that this expense is from.
    public Event getFromEvent() {
        return this.fromEvent;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", expenseName);
        json.put("amount", amount);
        json.put("paidBy", paidBy.toJson());
        json.put("sharedBy", sharedByToJson());
        json.put("fromEvent", fromEvent.toJson());

        return json;
    }

    private JSONArray sharedByToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Person person : sharedBy) {
            jsonArray.put(person.toJson());
        }
        return jsonArray;
    }

}
