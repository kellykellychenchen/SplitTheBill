package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Person represents a person who participate in events and pays/shares the cost of expenses in that event.
public class Person implements Writable {
    private String name;
    private Double totalPaid;
    private Double totalShared;
    private Double balance;

    // EFFECTS: constructs a person with the given name and an empty list of events.
    public Person(String name) {
        this.name = name;
        this.totalPaid = 0.0;
        this.totalShared = 0.0;
        this.balance = 0.0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotalPaid(Double totalPaid) {
        this.totalPaid = totalPaid;
    }

    public void setTotalShared(Double totalShared) {
        this.totalShared = totalShared;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getName() {
        return this.name;
    }

    public Double getTotalPaid() {
        return totalPaid;
    }

    public Double getTotalShared() {
        return totalShared;
    }

    public Double getBalance() {
        return balance;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        //TODO: other fields
        return json;
    }

}
