package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// SpendingEvent class represents an event with an event name, a list of people that participated in the event, and
// list of expenses associated with the event.
public class SpendingEvent implements Writable {
    private String eventName;
    private ArrayList<Person> people;
    private ArrayList<Expense> expenses;

    // EFFECTS: constructs an event with a given event name, an empty list of people, and an empty list of expenses.
    public SpendingEvent(String eventName) {
        this.eventName = eventName;
        this.people = new ArrayList<>();
        this.expenses = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a given person to this event's people list.
    public void addPerson(Person p) {
        people.add(p);
        EventLog.getInstance().logEvent(new Event("Added person: " + p.getName() + " to spendingEvent: "
                + this.eventName));
    }

    // MODIFIES: this
    // EFFECTS: adds a given expense to the event's expense list.
    public void addExpense(Expense ex) {
        expenses.add(ex);
        EventLog.getInstance().logEvent(new Event("Added expense: " + ex.getExpenseName()
                + " to spendingEvent: " + this.eventName));
    }

    // EFFECTS: returns the total cost of all expenses in this event.
    public double calcTotalCost() {
        double result = 0;
        for (Expense e : this.expenses) {
            result += e.getAmount();
        }
        return result;
    }

    // MODIFIES: this
    // EFFECTS: recalculates the balance of all people in this event with the current list of expenses.
    public void reCalculateBalance() {
        for (Person p : this.people) {
            calcPersonBalance(p);
        }
        EventLog.getInstance().logEvent(new Event("Re-calculated each person's balance for spendingEvent: "
                + this.eventName));
    }

    // MODIFIES: Person p
    // EFFECTS: calculates the total amount paid by p, total amount shared by p, and p's balance based on the current
    // list of expenses in this event and sets p's totalPaid, totalShared, and balance fields accordingly.
    public void calcPersonBalance(Person p) {
        double paid = 0;
        for (Expense e : expenses) {
            if (e.getPaidBy().equals(p)) {
                paid += e.getAmount();
            }
        }
        p.setTotalPaid(paid);

        double shared = 0;
        for (Expense e : expenses) {
            if (e.getSharedBy().contains(p)) {
                shared += e.splitAmount();
            }
        }
        p.setTotalShared(shared);

        p.setBalance(paid - shared);
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    public ArrayList<Expense> getExpenses() {
        return expenses;
    }

    // EFFECTS: Returns this event as a JSON object.
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", eventName);
        json.put("people", peopleToJson());
        json.put("expenses", expensesToJson());
        return json;
    }

    // EFFECTS: Returns the list of people in this event as a JSON array.
    private JSONArray peopleToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Person person : people) {
            jsonArray.put(person.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: Returns the list of expenses in this event as a JSON array.
    private JSONArray expensesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Expense expense : expenses) {
            jsonArray.put(expense.toJson());
        }
        return jsonArray;
    }
}
