package model;

import java.util.ArrayList;

public class Expense {
    private String expenseName;
    private int amount;
    private Person paidBy;
    private ArrayList<Person> sharedBy;

    public Expense(String expenseName, int amount, Person paidBy, ArrayList<Person> sharedBy) {
        this.expenseName = expenseName;
        this.amount = amount;
        this.paidBy = paidBy;
        this.sharedBy = sharedBy;
    }

    // split amount evenly across sharedBy users
    public int splitAmount() {
        return amount / sharedBy.size();
    }

    //SETTERS:
    public void setExpenseName(String expNam) {
        this.expenseName = expNam;
    }


    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPaidBy(Person paidBy) {
        this.paidBy = paidBy;
    }

    public void setSharedBy(ArrayList<Person> sharedBy) {
        this.sharedBy = sharedBy;
    }


    //GETTERS:
    public String getExpenseName() {
        return this.expenseName;
    }

    public int getAmount() {
        return this.amount;
    }

    public Person getPaidBy() {
        return this.paidBy;
    }

    public ArrayList<Person> getSharedBy() {
        return this.sharedBy;
    }


}
