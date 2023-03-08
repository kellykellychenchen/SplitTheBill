package persistence;

import org.json.JSONObject;

// Writable is an interface of classes that can be written to file as JSONObjects.
public interface Writable {
    // EFFECTS: Returns the current object as a JSONObject.
    JSONObject toJson();
}
