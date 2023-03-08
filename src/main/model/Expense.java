package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Expense class represents an expense with an expense name, expense amount, a person who paid for the expense, and
// a list of people that share the cost of the expense.
public class Expense implements Writable {
    private String expenseName;
    private double amount;
    private Person paidBy;
    private ArrayList<Person> sharedBy;

    // EFFECTS: constructs an expense with the given expense name, given amount of the cost, a given Person that paid
    // for this expense, and a given list of people that the cost of this expense is to be shared amongst.
    public Expense(String expenseName, double amount, Person paidBy, ArrayList<Person> sharedBy) {
        this.expenseName = expenseName;
        this.amount = amount;
        this.paidBy = paidBy;
        this.sharedBy = sharedBy;
    }

    // EFFECTS: returns the shared cost per person by dividing the expense cost evenly amongst the list of people who
    // share this cost.
    public double splitAmount() {
        return amount / sharedBy.size();
    }

    public void setExpenseName(String expNam) {
        this.expenseName = expNam;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPaidBy(Person paidBy) {
        this.paidBy = paidBy;
    }

    public void setSharedBy(ArrayList<Person> sharedBy) {
        this.sharedBy = sharedBy;
    }

    public String getExpenseName() {
        return this.expenseName;
    }

    public double getAmount() {
        return this.amount;
    }

    public Person getPaidBy() {
        return this.paidBy;
    }

    public ArrayList<Person> getSharedBy() {
        return this.sharedBy;
    }

    // EFFECTS: Returns this expense as a JSON object.
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", expenseName);
        json.put("amount", amount);
        json.put("paidBy", paidBy.toJson());
        json.put("sharedBy", sharedByToJson());

        return json;
    }

    // EFFECTS: Returns people that share the cost of this expense as a JSON array.
    private JSONArray sharedByToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Person person : sharedBy) {
            jsonArray.put(person.toJson());
        }
        return jsonArray;
    }
}
