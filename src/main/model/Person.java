package model;

import java.util.ArrayList;

public class Person {
    private String name;
    private ArrayList<Event> events;

    public Person(String name) {
        this.name = name;
        events = new ArrayList<>();
    }

    public void addEvent(Event e) {
        this.events.add(e);
    }

    public int calcTotalPaid() {
        int paid = 0;
        for (Event e : events) {
            paid += e.calcTotalPaidByPerson(this);
        }
        return paid;
    }

    public int calcTotalShared() {
        int shared = 0;
        for (Event e : events) {
            shared += e.calcTotalSharedByPerson(this);
        }
        return shared;
    }

    public int calcTotalBalance() {
        return this.calcTotalPaid() - this.calcTotalShared();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Event> getEvents() {
        return this.events;
    }
}
