package model;

import java.util.ArrayList;

public class Event {
    private String eventName;
    private ArrayList<Person> people;
    private ArrayList<Expense> expenses;

    public Event() {
        this.eventName = "Unnamed Event";
        this.people = new ArrayList<Person>();
        this.expenses = new ArrayList<Expense>();
    }

    //give a name to event
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    //TODO: MAKE EXPENSE LIST AUTOMATICALLY ADD PEOPLE
    
    //add people
    public void addPerson(Person p) {
        people.add(p);
    }

    public void removePerson(Person p) {
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
    public int totalCost() {
        int result = 0;
        for (Expense e : expenses) {
            result += e.getAmount();
        }
        return result;
    }

    // calculate total amount paid by a person in an event
    public int totalPaidByPerson(Person p) {
        int result = 0;
        for (Expense e : expenses) {
            if (e.getPaidBy() == p) {
                result += e.getAmount();
            }
        }
        return result;
    }

    // calculate total shared cost for a person in an event
    public int totalSharedByPerson(Person p) {
        int result = 0;
        for (Expense e: expenses) {
            if (e.getSharedBy().contains(p)) {
                result += e.SplitAmount();
            }
        }
        return result;
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
