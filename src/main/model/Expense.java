package model;

import java.util.ArrayList;

public class Expense {
    private String expenseName;
    private String expenseDescription;
    private int amount;
    private Person paidBy;
    private ArrayList<Person> sharedBy;

    public Expense(int amount, Person paidBy, ArrayList<Person> sharedBy) {
        this.expenseName = "Unnamed Expense";
        this.expenseDescription = "none";
        this.amount = amount;
        this.paidBy = paidBy;
        this.sharedBy = sharedBy;
    }

    public int SplitAmount() {
        return amount / sharedBy.size();
    }

    public void setExpenseName (String expNam) {
        this.expenseName = expNam;
    }

    public void setExpenseDescription(String expDes) {
        this.expenseDescription = expDes;
    }

    public void setAmount (int amount) {
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

    public String getExpenseDescription() {
        return this.expenseDescription;
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
