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
        exp1 = new Expense("s1", 100, p1, lop);
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
        assertEquals(0, p1.getEvents().size());
        event1.addPerson(p1);
        assertEquals(1, event1.getPeople().size());
        assertEquals(p1, event1.getPeople().get(0));
        assertEquals(1, p1.getEvents().size());
        assertEquals(event1, p1.getEvents().get(0));

        event2.addPerson(p1);
        assertEquals(2, p1.getEvents().size());
        assertEquals(event1, p1.getEvents().get(0));
        assertEquals(event2, p1.getEvents().get(1));

        event1.addPerson(p2);
        assertEquals(2, event1.getPeople().size());
        assertEquals(p1, event1.getPeople().get(0));
        assertEquals(p2, event1.getPeople().get(1));
        assertEquals(1, p2.getEvents().size());
        assertEquals(event1, p2.getEvents().get(0));
    }

    @Test
    public void testAddExpense() {
        event1.addExpense(exp1);
        assertEquals(1, event1.getExpenses().size());
        assertEquals(exp1, event1.getExpenses().get(0));
        assertEquals(event1, exp1.getFromEvent());

        event1.addExpense(exp2);
        assertEquals(2, event1.getExpenses().size());
        assertEquals(exp1, event1.getExpenses().get(0));
        assertEquals(exp2, event1.getExpenses().get(1));
        assertEquals(event1, exp2.getFromEvent());
    }

    @Test
    public void testCalcTotal() {
        assertEquals(0, event1.calcTotalCost());
        event1.addExpense(exp1);
        assertEquals(100, event1.calcTotalCost());
        event1.addExpense(exp2);
        event1.addExpense(exp3);
        assertEquals(600, event1.calcTotalCost());
    }

    @Test
    public void testCalcTotalPaidByPerson() {
        assertEquals(0, event1.calcTotalPaidByPerson(p1));
        assertEquals(0, event1.calcTotalPaidByPerson(p2));

        event1.addExpense(exp1);
        assertEquals(100, event1.calcTotalPaidByPerson(p1));
        assertEquals(0, event1.calcTotalPaidByPerson(p2));

        event1.addExpense(exp2);
        assertEquals(100, event1.calcTotalPaidByPerson(p1));
        assertEquals(200, event1.calcTotalPaidByPerson(p2));

        event1.addExpense(exp3);
        assertEquals(400, event1.calcTotalPaidByPerson(p1));
        assertEquals(200, event1.calcTotalPaidByPerson(p2));
    }

    @Test
    public void testCalcTotalSharedByPerson() {
        assertEquals(0, event1.calcTotalSharedByPerson(p1));
        assertEquals(0, event1.calcTotalSharedByPerson(p2));

        event1.addExpense(exp1);
        assertEquals(50, event1.calcTotalSharedByPerson(p1));
        assertEquals(50, event1.calcTotalSharedByPerson(p2));

        event1.addExpense(exp2);
        assertEquals(250, event1.calcTotalSharedByPerson(p1));
        assertEquals(50, event1.calcTotalSharedByPerson(p2));

        event1.addExpense(exp3);
        assertEquals(250, event1.calcTotalSharedByPerson(p1));
        assertEquals(350, event1.calcTotalSharedByPerson(p2));
    }

    @Test
    public void testCalcBalance() {
        assertEquals(0, event1.calcBalance(p1));
        assertEquals(0, event1.calcBalance(p2));

        event1.addExpense(exp1);
        assertEquals(50, event1.calcBalance(p1));
        assertEquals(-50, event1.calcBalance(p2));

        event1.addExpense(exp2);
        assertEquals(-150, event1.calcBalance(p1));
        assertEquals(150, event1.calcBalance(p2));

        event1.addExpense(exp3);
        assertEquals(150, event1.calcBalance(p1));
        assertEquals(-150, event1.calcBalance(p2));
    }
}