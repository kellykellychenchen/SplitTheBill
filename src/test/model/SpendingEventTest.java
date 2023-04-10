package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Tests for the SpendingEvent class.
class SpendingEventTest {
    SpendingEvent spendingEvent1;
    SpendingEvent spendingEvent2;
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
        spendingEvent1 = new SpendingEvent("spendingEvent1");
        spendingEvent2 = new SpendingEvent("spendingEvent2");
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
        assertEquals("spendingEvent1", spendingEvent1.getEventName());
        assertEquals(0, spendingEvent1.getExpenses().size());
        assertEquals(0, spendingEvent1.getPeople().size());
    }

    @Test
    public void testAddPerson() {
        spendingEvent1.addPerson(p1);
        assertEquals(1, spendingEvent1.getPeople().size());
        assertEquals(p1, spendingEvent1.getPeople().get(0));

        spendingEvent2.addPerson(p1);
        assertEquals(1, spendingEvent2.getPeople().size());
        assertEquals(p1, spendingEvent2.getPeople().get(0));

        spendingEvent1.addPerson(p2);
        assertEquals(2, spendingEvent1.getPeople().size());
        assertEquals(p1, spendingEvent1.getPeople().get(0));
        assertEquals(p2, spendingEvent1.getPeople().get(1));
    }

    @Test
    public void testAddExpense() {
        spendingEvent1.addExpense(exp1);
        assertEquals(1, spendingEvent1.getExpenses().size());
        assertEquals(exp1, spendingEvent1.getExpenses().get(0));

        spendingEvent1.addExpense(exp2);
        assertEquals(2, spendingEvent1.getExpenses().size());
        assertEquals(exp1, spendingEvent1.getExpenses().get(0));
        assertEquals(exp2, spendingEvent1.getExpenses().get(1));
    }

    @Test
    public void testCalcTotalCost() {
        assertEquals(0, spendingEvent1.calcTotalCost());
        spendingEvent1.addExpense(exp1);
        assertEquals(102, spendingEvent1.calcTotalCost());
        spendingEvent1.addExpense(exp2);
        spendingEvent1.addExpense(exp3);
        assertEquals(602, spendingEvent1.calcTotalCost());
    }


    @Test
    public void testCalculateBalanceZeroOneExp() {
        spendingEvent1.addPerson(p1);
        spendingEvent1.addPerson(p2);
        spendingEvent1.reCalculateBalance();
        assertEquals(0, p1.getTotalPaid());
        assertEquals(0, p1.getTotalShared());
        assertEquals(0, p1.getBalance());
        assertEquals(0, p2.getTotalPaid());
        assertEquals(0, p2.getTotalShared());
        assertEquals(0, p2.getBalance());

        spendingEvent1.addExpense(exp1);
        spendingEvent1.reCalculateBalance();
        assertEquals(102, p1.getTotalPaid());
        assertEquals(51, p1.getTotalShared());
        assertEquals(51, p1.getBalance());
        assertEquals(0, p2.getTotalPaid());
        assertEquals(51, p2.getTotalShared());
        assertEquals(-51, p2.getBalance());
    }

    @Test
    public void testCalculateBalanceTwoThreeExp() {
        spendingEvent1.addPerson(p1);
        spendingEvent1.addPerson(p2);
        spendingEvent1.addExpense(exp1);
        spendingEvent1.addExpense(exp2);
        spendingEvent1.reCalculateBalance();
        assertEquals(102, p1.getTotalPaid());
        assertEquals(251, p1.getTotalShared());
        assertEquals(-149, p1.getBalance());
        assertEquals(200, p2.getTotalPaid());
        assertEquals(51, p2.getTotalShared());
        assertEquals(149, p2.getBalance());

        spendingEvent1.addExpense(exp3);
        spendingEvent1.reCalculateBalance();
        assertEquals(402, p1.getTotalPaid());
        assertEquals(251, p1.getTotalShared());
        assertEquals(151, p1.getBalance());
        assertEquals(200, p2.getTotalPaid());
        assertEquals(351, p2.getTotalShared());
        assertEquals(-151, p2.getBalance());
    }

    @Test
    public void testSetEventName() {
        spendingEvent1.setEventName("something else");
        assertEquals("something else", spendingEvent1.getEventName());
    }
}