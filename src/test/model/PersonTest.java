package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Tests for methods in the person class.
public class PersonTest {
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
        exp1 = new Expense("s1", 100, p1, lop);
        exp2 = new Expense("s2", 200, p2, lop1);
        exp3 = new Expense("s3", 300, p1, lop2);
    }

    @Test
    public void testConstructor() {
        assertEquals("name1", p1.getName());
        assertEquals(0, p1.getEvents().size());
    }

    @Test
    public void testAddEvent() {
        p1.addEvent(event1);
        assertEquals(1, p1.getEvents().size());
        assertEquals("event1", p1.getEvents().get(0).getEventName());

        p1.addEvent(event2);
        assertEquals(2, p1.getEvents().size());
        assertEquals("event1", p1.getEvents().get(0).getEventName());
        assertEquals("event2", p1.getEvents().get(1).getEventName());
    }

    @Test
    public void testGetTotalPaid() {
        assertEquals(0, p1.calcTotalPaid());

        p1.addEvent(event1);
        event1.addExpense(exp1);
        assertEquals(100, p1.calcTotalPaid());

        event1.addExpense(exp2);
        assertEquals(100, p1.calcTotalPaid());

        event1.addExpense(exp3);
        assertEquals(400, p1.calcTotalPaid());

        p1.addEvent(event2);
        event2.addExpense(exp1);
        assertEquals(500, p1.calcTotalPaid());
    }

    @Test
    public void testGetTotalShared() {
        assertEquals(0, p1.calcTotalShared());

        p1.addEvent(event1);
        event1.addExpense(exp1);
        assertEquals(50, p1.calcTotalShared());

        event1.addExpense(exp2);
        assertEquals(250, p1.calcTotalShared());

        event1.addExpense(exp3);
        assertEquals(250, p1.calcTotalShared());

        p1.addEvent(event2);
        event2.addExpense(exp1);
        assertEquals(300, p1.calcTotalShared());
    }

    @Test
    public void testGetTotalBalance() {
        assertEquals(0, p1.calcTotalBalance());

        p1.addEvent(event1);
        event1.addExpense(exp1);
        assertEquals(50, p1.calcTotalBalance());

        event1.addExpense(exp2);
        assertEquals(-150, p1.calcTotalBalance());

        event1.addExpense(exp3);
        assertEquals(150, p1.calcTotalBalance());

        p1.addEvent(event2);
        event2.addExpense(exp1);
        assertEquals(200, p1.calcTotalBalance());
    }

    @Test
    public void testSetName() {
        p1.setName("not_p1");
        assertEquals("not_p1", p1.getName());
        p1.setName("p1");
        assertEquals("p1", p1.getName());
    }

}
