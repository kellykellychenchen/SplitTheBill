package model;

import java.util.ArrayList;

public class Person {
    private String name;
    private int balance;
    // private ArrayList<Event> events;
    // private ArrayList<Expense> expenses;
    // wishlist - return this person's balance for each event

    public Person(String name) {
        this.name = name;
        this.balance = 0;
    }

    public String getName() {
        return this.name;
    }

    public int getBalance() {
        return this.balance;
    }
}
