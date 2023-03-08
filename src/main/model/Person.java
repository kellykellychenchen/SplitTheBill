package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Person class represents a person with a name, amount paid, amount shared, and amount balance.
public class Person implements Writable {
    private String name;
    private Double totalPaid;
    private Double totalShared;
    private Double balance;

    // EFFECTS: constructs a person with the given name and zero as the initial amount paid, shared, and balance.
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

    // EFFECTS: Returns this person as a JSON object.
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("totalPaid", totalPaid);
        json.put("totalShared", totalShared);
        json.put("balance", balance);
        return json;
    }

}
