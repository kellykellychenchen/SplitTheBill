package model;

import java.util.ArrayList;

// Person represents a person who participate in events and pays/shares the cost of expenses in that event.
public class Person {
    private String name;
    private ArrayList<Event> events;

    // EFFECTS: constructs a person with the given name and an empty list of events.
    public Person(String name) {
        this.name = name;
        events = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds the given event to this person's list of events.
    public void addEvent(Event e) {
        this.events.add(e);
    }

    // EFFECTS: returns an integer representing the total amount paid by this person across all events that this person
    // is associated with.
    public int calcTotalPaid() {
        int paid = 0;
        for (Event e : events) {
            paid += e.calcTotalPaidByPerson(this);
        }
        return paid;
    }

    // EFFECTS: returns an integer representing the total amount shared by this person across all events that this
    // person is associated with.
    public int calcTotalShared() {
        int shared = 0;
        for (Event e : events) {
            shared += e.calcTotalSharedByPerson(this);
        }
        return shared;
    }

    // EFFECTS: returns an integer representing the total balance for this person across all events that this person is
    // associated with. A positive amount indicates the person is entitled to receive this amount from others. A
    // negative amount means this person owes that amount to others.
    public int calcTotalBalance() {
        return this.calcTotalPaid() - this.calcTotalShared();
    }

    // MODIFIES: this
    // EFFECTS: re-sets the name of this person to the given name.
    public void setName(String name) {
        this.name = name;
    }

    // EFFECTS: returns the name of this person.
    public String getName() {
        return this.name;
    }

    // EFFECTS: returns the list of events that this person is associated with.
    public ArrayList<Event> getEvents() {
        return this.events;
    }
}
