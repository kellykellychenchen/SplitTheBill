package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Objects;

// Person class represents a person with a name, amount paid, amount shared, and amount balance.
public class Person implements Writable {
    private String name;
    private double totalPaid;
    private double totalShared;
    private double balance;

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

    public void setTotalPaid(double totalPaid) {
        this.totalPaid = totalPaid;
    }

    public void setTotalShared(double totalShared) {
        this.totalShared = totalShared;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getName() {
        return this.name;
    }

    public double getTotalPaid() {
        return totalPaid;
    }

    public double getTotalShared() {
        return totalShared;
    }

    public double getBalance() {
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
