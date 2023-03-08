package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Tests for methods in the event class.
class EventTest {
    Event event1;
    Event event2;
    Expense exp1;
    Expense exp2;
    Expense exp3;
    Person p1;
    Person p2;
    ArrayList<Person> lop;
    ArrayList<Person> lop1;
    ArrayList<Person> lop2;

    @BeforeEach
    public void setUp() {
        event1 = new Event("event1");
        event2 = new Event("event2");
        p1 = new Person("name1");
        p2 = new Person("name2");
        lop = new ArrayList<>();
        lop.add(p1);
        lop.add(p2);
        lop1 = new ArrayList<>();
        lop1.add(p1);
        lop2 = new ArrayList<>();
        lop2.add(p2);
        exp1 = new Expense("s1", 102, p1, lop);
        exp2 = new Expense("s2", 200, p2, lop1);
        exp3 = new Expense("s3", 300, p1, lop2);
    }

    @Test
    public void testConstructor() {
        assertEquals("event1", event1.getEventName());
        assertEquals(0, event1.getExpenses().size());
        assertEquals(0, event1.getPeople().size());
    }

    @Test
    public void testSetEventName() {
        event1.setEventName("something else");
        assertEquals("something else", event1.getEventName());
    }

    @Test
    public void testAddPerson() {
        event1.addPerson(p1);
        assertEquals(1, event1.getPeople().size());
        assertEquals(p1, event1.getPeople().get(0));

        event2.addPerson(p1);
        assertEquals(1, event2.getPeople().size());
        assertEquals(p1, event2.getPeople().get(0));

        event1.addPerson(p2);
        assertEquals(2, event1.getPeople().size());
        assertEquals(p1, event1.getPeople().get(0));
        assertEquals(p2, event1.getPeople().get(1));
    }

    @Test
    public void testAddExpense() {
        event1.addExpense(exp1);
        assertEquals(1, event1.getExpenses().size());
        assertEquals(exp1, event1.getExpenses().get(0));

        event1.addExpense(exp2);
        assertEquals(2, event1.getExpenses().size());
        assertEquals(exp1, event1.getExpenses().get(0));
        assertEquals(exp2, event1.getExpenses().get(1));
    }

    @Test
    public void testCalcTotal() {
        assertEquals(0, event1.calcTotalCost());
        event1.addExpense(exp1);
        assertEquals(102, event1.calcTotalCost());
        event1.addExpense(exp2);
        event1.addExpense(exp3);
        assertEquals(602, event1.calcTotalCost());
    }

    @Test
    public void testCalcPersonBalance() {
        event1.calcPersonBalance(p1);
        assertEquals(0, p1.getTotalPaid());
        assertEquals(0, p1.getTotalShared());

        event1.addExpense(exp1);
        event1.calcPersonBalance(p1);
        assertEquals(102, p1.getTotalPaid());
        assertEquals(51, p1.getTotalShared());
        assertEquals(51, p1.getBalance());

        event1.addExpense(exp2);
        event1.calcPersonBalance(p1);
        assertEquals(102, p1.getTotalPaid());
        assertEquals(251, p1.getTotalShared());
        assertEquals(-149, p1.getBalance());

        event1.addExpense(exp3);
        event1.calcPersonBalance(p1);
        event1.calcPersonBalance(p2);
        assertEquals(402, p1.getTotalPaid());
        assertEquals(251, p1.getTotalShared());
        assertEquals(200, p2.getTotalPaid());
        assertEquals(351, p2.getTotalShared());
        assertEquals(151, p1.getBalance());
        assertEquals(-151, p2.getBalance());
    }
}