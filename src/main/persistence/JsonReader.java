package persistence;

import model.BillBook;
import model.Event;
import model.Expense;
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

// Represents a reader that reads workroom from JSON data stored in file
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
        addEventsToBillBook(bb, jsonObject);
        return bb;
    }

    // MODIFIES: bb
    // EFFECTS: parse events from JSON object and adds them to billbook
    private void addEventsToBillBook(BillBook bb, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("events");
        for (Object json : jsonArray) {
            JSONObject nextEvent = (JSONObject) json;
            addEventToBillBook(bb, nextEvent);
        }
    }

    // MODIFIES: bb
    // EFFECTS: parse event from JSON object and add it to billbook
    private void addEventToBillBook(BillBook bb, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Event event = new Event(name);
        addPeopleToEvent(event, jsonObject);
        addExpensesToEvent(event, jsonObject);
        bb.addEvent(event);
    }

    // MODIFIES: event
    // EFFECTS: parse people from JSON object and adds them to event
    private void addPeopleToEvent(Event event, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("people");
        for (Object json : jsonArray) {
            JSONObject nextPerson = (JSONObject) json;
            addPersonToEvent(event, nextPerson);
        }
    }

    // MODIFIES: event
    // EFFECTS: parse person from JSON object and add it to event
    private void addPersonToEvent(Event event, JSONObject jsonObject) {
        Person person = parsePerson(jsonObject);
        event.addPerson(person);
    }

    // MODIFIES: event
    // EFFECTS: parse expenses from JSON object and adds them to event
    private void addExpensesToEvent(Event event, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("expenses");
        for (Object json : jsonArray) {
            JSONObject nextExpense = (JSONObject) json;
            addExpenseToEvent(event, nextExpense);
        }
    }

    // MODIFIES: event
    // EFFECTS: parse expense from JSON object and add it to event
    private void addExpenseToEvent(Event event, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double amount = jsonObject.getDouble("amount");
        Person paidBy = parsePerson((JSONObject) jsonObject.get("paidBy"));
        ArrayList sharedBy = new ArrayList<>();
        addPeopleToSharedBy(sharedBy, jsonObject);

        Expense expense = new Expense(name, amount, paidBy, sharedBy);
        event.addExpense(expense);
    }

    // MODIFIES: sharedBy
    // EFFECTS: parse people from JSON object and adds them to sharedBy
    private void addPeopleToSharedBy(List sharedBy, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("sharedBy");
        for (Object json : jsonArray) {
            JSONObject nextPerson = (JSONObject) json;
            addPersonToSharedBy(sharedBy, nextPerson);
        }
    }
    
    // MODIFIES: sharedBy
    // EFFECTS: parse person from JSON object and adds them to sharedBy
    private void addPersonToSharedBy(List sharedBy, JSONObject jsonObject) {
        Person person = parsePerson(jsonObject);
        sharedBy.add(person);
    }

    // EFFECTS: parse person from JSON object and returns it as Person
    private Person parsePerson(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double totalPaid = jsonObject.getDouble("totalPaid");
        double totalShared = jsonObject.getDouble("totalShared");
        double balance = jsonObject.getDouble("balance");
        Person person = new Person(name);
        person.setTotalPaid(totalPaid);
        person.setTotalShared(totalShared);
        person.setBalance(balance);
        return person;
    }
}
