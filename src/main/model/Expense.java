package model;

import java.util.ArrayList;

// Expense represents an expense which has an expense name, an amount, a person who paid for the expense, and a list of
// people that share the cost of this expense.
public class Expense {
    private String expenseName;
    private int amount;
    private Person paidBy;
    private ArrayList<Person> sharedBy;

    // EFFECTS: constructs an expense with the given expense name, given integer representing the amount of the expense,
    // a given Person who paid for this expense, and a given list of people that the cost of this expense is shared
    // between.
    public Expense(String expenseName, int amount, Person paidBy, ArrayList<Person> sharedBy) {
        this.expenseName = expenseName;
        this.amount = amount;
        this.paidBy = paidBy;
        this.sharedBy = sharedBy;
    }

    // EFFECTS: returns an integer representing the shared cost per person by dividing the expense cost evenly amongst
    // the list of people who shares this cost.
    public int splitAmount() {
        return amount / sharedBy.size();
    }

    // MODIFIES: this
    // EFFECTS: re-names the expense with the given name.
    public void setExpenseName(String expNam) {
        this.expenseName = expNam;
    }

    // MODIFIES: this
    // EFFECTS: re-sets the expense amount with the given amount.
    public void setAmount(int amount) {
        this.amount = amount;
    }

    // MODIFIES: this
    // EFFECTS: re-sets the person who paid for this expense with the given person.
    public void setPaidBy(Person paidBy) {
        this.paidBy = paidBy;
    }

    // MODIFIES: this
    // EFFECTS: re-sets the list of people that share this expense with the new list of people.
    public void setSharedBy(ArrayList<Person> sharedBy) {
        this.sharedBy = sharedBy;
    }

    // EFFECTS: returns the name of this expense.
    public String getExpenseName() {
        return this.expenseName;
    }

    // EFFECTS: returns an integer representing the cost of this expense.
    public int getAmount() {
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


}
