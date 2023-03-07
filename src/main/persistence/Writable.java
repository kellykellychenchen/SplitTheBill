package persistence;

import org.json.JSONObject;

public interface Writable {
    // a method called toJson() that returns type JSONObject
    JSONObject toJson();
}
