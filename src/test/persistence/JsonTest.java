package persistence;

import model.Event;
import model.Expense;
import model.Person;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

// tests for Json
public class JsonTest {
    protected void checkEvent(Event ev, String name, ArrayList<Person> people, ArrayList<Expense> expenses) {
        assertEquals(name, ev.getEventName());
        checkPeople(people, ev.getPeople());
        checkExpenses(expenses, ev.getExpenses());
    }

    private void checkPeople(ArrayList<Person> ppl, ArrayList<Person> ppl2) {
        int i = 0;
        for (Person per : ppl) {
            checkPerson(per, ppl2.get(i));
            i++;
        }
    }

    protected void checkPerson(Person p, Person p2) {
        assertEquals(p2.getName(), p.getName());
        assertEquals(p2.getTotalPaid(), p.getTotalPaid());
        assertEquals(p2.getTotalShared(), p.getTotalShared());
        assertEquals(p2.getBalance(), p.getBalance());
    }

    private void checkExpenses(ArrayList<Expense> expenses, ArrayList<Expense> expenses2) {
        int i = 0;
        for (Expense exp : expenses) {
            checkExpense(exp, expenses2.get(i));
            i++;
        }
    }

    protected void checkExpense(Expense ex, Expense ex2) {
        assertEquals(ex2.getExpenseName(), ex.getExpenseName());
        assertEquals(ex2.getAmount(), ex.getAmount());
        checkPerson(ex2.getPaidBy(), ex.getPaidBy());
        checkPeople(ex2.getSharedBy(), ex.getSharedBy());
    }

}
