package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {
    Event event1;
    Expense exp1;
    Expense exp2;
    Expense exp3;
    Person p1;
    Person p2;
    ArrayList<Person> lop1;

    @BeforeEach
    public void setUp() {
        event1 = new Event("event1");
        p1 = new Person("name1");
        p2 = new Person("name2");
        lop1 = new ArrayList<Person>();
        lop1.add(p1);
        lop1.add(p2);
        exp1 = new Expense("s1",100,p1,lop1);
        exp2 = new Expense("s2", 200,p2,lop1);
        exp3 = new Expense("s3", 300,p1,lop1);
    }

    @Test
    public void testAddExpense() {
        event1.addExpense(exp1);
        event1.addExpense(exp2);
        event1.addExpense(exp3);
        ArrayList<Expense> expected = new ArrayList<Expense>();
        expected.add(exp1);
        expected.add(exp2);
        expected.add(exp3);
        assertEquals(expected.get(0),event1.getExpenses().get(0));
        assertEquals(expected.get(1),event1.getExpenses().get(1));
        assertEquals(expected.get(2),event1.getExpenses().get(2));
        assertEquals(expected.size(),event1.getExpenses().size());
    }

    @Test
    public void testCalcTotal() {
        event1.addExpense(exp1);
        event1.addExpense(exp2);
        event1.addExpense(exp3);
        assertEquals(600, event1.calcTotalCost());
    }

}