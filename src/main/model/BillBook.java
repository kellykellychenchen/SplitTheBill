package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//TODO: class level & method comments
public class BillBook implements Writable {
    private String name;
    private List<Event> events;

    // EFFECTS: constructs a billbook with a name and empty list of event
    public BillBook(String name) {
        this.name = name;
        events = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addEvent (Event event) {
        events.add(event);
    }

    public List<Event> getEvents() {
        return Collections.unmodifiableList(events);
    }

    public int numEvents() {
        return events.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("events", eventsToJson());
        return json;
    }

    private JSONArray eventsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Event event : events) {
            jsonArray.put(event.toJson());
        }

        return jsonArray;
    }
}
