package model;

import java.util.ArrayList;
import java.util.List;

public class Event {
    private String eventName;
    private ArrayList<Person> people;
    private ArrayList<Expense> expenses;

    public Event(String eventName) {
        this.eventName = eventName;
        this.people = new ArrayList<Person>();
        this.expenses = new ArrayList<Expense>();
    }

    //rename the event
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    //add person
    public void addPerson (Person p) {
        people.add(p);
    }

    //remove person
    public void removePerson (Person p) {
        people.remove(p);
    }

    //add expense
    public void addExpense (Expense e) {
        expenses.add(e);
    }

    public void removeExpense (Expense e) {
        expenses.remove(e);
    }

    //calculate total cost
    public int calcTotalCost() {
        int result = 0;
        for (Expense e : this.expenses) {
            result += e.getAmount();
        }
        return result;
    }

    // calculate total amount paid by a person in an event
    public int calcTotalPaidByPerson(Person p) {
        int result = 0;
        for (Expense e : expenses) {
            if (e.getPaidBy() == p) {
                result += e.getAmount();
            }
        }
        return result;
    }

    // calculate total shared cost for a person in an event
    public int calcTotalSharedByPerson(Person p) {
        int result = 0;
        for (Expense e: expenses) {
            if (e.getSharedBy().contains(p)) {
                result += e.SplitAmount();
            }
        }
        return result;
    }

    // calculate balance for a person in an event. Positive means receivable. Negative means owing.
    public int calcBalance(Person p) {
        return calcTotalPaidByPerson(p) - calcTotalSharedByPerson(p);
    }

    // getters
    public String getEventName() {
        return eventName;
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    public ArrayList<Expense> getExpenses() {
        return expenses;
    }

}
