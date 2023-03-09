package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a billbook with a collection of events.
public class BillBook implements Writable {
    private String name;
    private List<Event> events;

    // EFFECTS: constructs a billbook with a name and empty list of event.
    public BillBook(String name) {
        this.name = name;
        events = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: adds event to this bill book's events.
    public void addEvent(Event event) {
        events.add(event);
    }

    // EFFECTS: returns an unmodifiable list of events in this billbook.
    public List<Event> getEvents() {
        return Collections.unmodifiableList(events);
    }

    // EFFECTS: returns the number of events in this billbook.
    public int numEvents() {
        return events.size();
    }

    // EFFECTS: Returns this billbook as a JSON object.
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("events", eventsToJson());
        return json;
    }

    // EFFECTS: Returns the events in this billbook as a JSON array.
    private JSONArray eventsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Event event : events) {
            jsonArray.put(event.toJson());
        }

        return jsonArray;
    }
}
