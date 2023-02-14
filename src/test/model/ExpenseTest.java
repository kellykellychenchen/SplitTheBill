package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpenseTest {
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
        assertEquals("s1", exp1.getExpenseName());
        assertEquals(100, exp1.getAmount());
        assertEquals(p1, exp1.getPaidBy());
        assertEquals(lop, exp1.getSharedBy());
    }

    @Test
    public void testSplitAmount() {
        assertEquals(50, exp1.splitAmount());
        assertEquals(200, exp2.splitAmount());
    }

    @Test
    public void testSetExpenseName() {
        exp1.setExpenseName("not_exp1");
        assertEquals("not_exp1", exp1.getExpenseName());
    }

    @Test
    public void testSetAmount() {
        exp1.setAmount(500);
        assertEquals(500, exp1.getAmount());
    }

    @Test
    public void testSetPaidBy() {
        exp1.setPaidBy(p2);
        assertEquals(p2, exp1.getPaidBy());
    }

    @Test
    public void testSetSharedBy() {
        exp1.setSharedBy(lop2);
        assertEquals(lop2, exp1.getSharedBy());
    }

}
