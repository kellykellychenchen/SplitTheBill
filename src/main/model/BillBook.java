package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a billbook with a collection of spendingEvents.
public class BillBook implements Writable {
    private String name;
    private List<SpendingEvent> spendingEvents;

    // EFFECTS: constructs a billbook with a name and empty list of event.
    public BillBook(String name) {
        this.name = name;
        spendingEvents = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: adds spendingEvent to this bill book's spendingEvents.
    public void addEvent(SpendingEvent spendingEvent) {
        spendingEvents.add(spendingEvent);
        EventLog.getInstance().logEvent(new Event("Added spendingEvent: " + spendingEvent.getEventName()));
    }

    // MODIFIES: this
    // EFFECTS: removes given spendingEvent from bill book's spendingEvents.
    public void removeEvent(SpendingEvent spendingEvent) {
        spendingEvents.remove(spendingEvent);
        EventLog.getInstance().logEvent(new Event("Removed spendingEvent: " + spendingEvent.getEventName()));

    }

    // MODIFIES: this
    // EFFECTS: removes all spendingEvents from bill book's spendingEvents.
    public void clearEvents() {
        spendingEvents.clear();
        EventLog.getInstance().logEvent(new Event("Cleared all spendingEvents"));
    }

    // EFFECTS: returns an unmodifiable list of spendingEvents in this billbook.
    public List<SpendingEvent> getEvents() {
        return Collections.unmodifiableList(spendingEvents);
    }

    // EFFECTS: returns the number of spendingEvents in this billbook.
    public int numEvents() {
        return spendingEvents.size();
    }

    // EFFECTS: Returns this billbook as a JSON object.
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("spendingEvents", eventsToJson());
        return json;
    }

    // EFFECTS: Returns the spendingEvents in this billbook as a JSON array.
    private JSONArray eventsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (SpendingEvent spendingEvent : spendingEvents) {
            jsonArray.put(spendingEvent.toJson());
        }

        return jsonArray;
    }
}
