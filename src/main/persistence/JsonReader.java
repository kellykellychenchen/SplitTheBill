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
        Event event = new Event("name");
        addPeopleToEvent(event, jsonObject);
        addExpensesToEvent(event, jsonObject);
        bb.addEvent(event);
    }

    private void addPeopleToEvent(Event event, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("people");
        for (Object json: jsonArray) {
            JSONObject nextPerson = (JSONObject) json;
            addPersonToEvent(event, nextPerson);
        }
    }

    private void addPersonToEvent(Event event, JSONObject jsonObject) {
        Person person = getPerson(jsonObject);
        event.addPerson(person);
    }

    private void addExpensesToEvent(Event event, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("expenses");
        for (Object json: jsonArray) {
            JSONObject nextExpense = (JSONObject) json;
            addExpenseToEvent(event, nextExpense);
        }
    }

    private void addExpenseToEvent(Event event, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Double amount = jsonObject.getDouble("amount");
        Person paidBy = getPerson((JSONObject) jsonObject.get("paidBy"));
        ArrayList sharedBy = new ArrayList<>();
        addPeopleToExpense(sharedBy, jsonObject);

        Expense expense = new Expense(name,amount,paidBy,sharedBy);
        event.addExpense(expense);
    }

    private void addPeopleToExpense(List people, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("sharedBy");
        for (Object json: jsonArray) {
            JSONObject nextPerson = (JSONObject) json;
            addPersonToList(people, nextPerson);
        }
    }

    private void addPersonToList(List people, JSONObject jsonObject) {
        Person person = getPerson(jsonObject);
        people.add(person);
    }

    // PARSE PERSON CLASS
    private Person getPerson(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Double totalPaid = jsonObject.getDouble("totalPaid");
        Double totalShared = jsonObject.getDouble("totalShared");
        Double balance = jsonObject.getDouble("balance");
        Person person = new Person(name);
        person.setTotalPaid(totalPaid);
        person.setTotalShared(totalShared);
        person.setBalance(balance);
        return person;
    }
}
