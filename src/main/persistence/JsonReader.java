package persistence;

import model.BillBook;
import model.Event;
import model.Person;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads billbook from file and returns it;
    // throws IOException if an error occurs reading data from file
    public BillBook read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBillBook(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String fileSource) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(fileSource), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parse billbook from JSON object and returns it
    private BillBook parseBillBook(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        BillBook bb = new BillBook(name);
        addEvents(bb, jsonObject);
        return bb;
    }

    // MODIFIES: bb
    // EFFECTS: parse events from JSON object and adds them to billbook
    private void addEvents(BillBook bb, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("events");
        for (Object json : jsonArray) {
            JSONObject nextEvent = (JSONObject) json;
            addEvent(bb, nextEvent);
        }
    }

    // MODIFIES: bb
    // EFFECTS: parse event from JSON object and add it to billbook
    private void addEvent(BillBook bb, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        ArrayList<Person> people = (ArrayList<Person>) jsonObject.get("people");
        //TODO: add expenses
        Event event = new Event("name");
        //TODO: add people & expenses

    }

}
