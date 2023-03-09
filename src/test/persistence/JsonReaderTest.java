package persistence;

import model.BillBook;
import model.Event;
import model.Expense;
import model.Person;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// tests for JsonReader
public class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            BillBook bb = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyBillBook() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyBillBook.json");
        try {
            BillBook bb = reader.read();
            assertEquals("test billbook", bb.getName());
            assertEquals(0, bb.numEvents());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralBillBook() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralBillBook.json");
        try {
            BillBook bb = reader.read();
            assertEquals("test billbook", bb.getName());
            List<Event> events = bb.getEvents();
            assertEquals(2, events.size());

            Event ev1 = setUpEvent1();
            Event ev2 = new Event("empty event");

            checkEvent(events.get(0), ev1.getEventName(), ev1.getPeople(), ev1.getExpenses());
            checkEvent(events.get(1), ev2.getEventName(), ev2.getPeople(), ev2.getExpenses());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    private Event setUpEvent1() {
        Person p1 = new Person("p1");
        Person p2 = new Person("p2");
        ArrayList<Person> sharedBy = new ArrayList<>();
        sharedBy.add(p1);
        sharedBy.add(p2);
        Expense ex1 = new Expense("ex1", 100, p1, sharedBy);
        Event ev1 = new Event("ev1");
        ev1.addPerson(p1);
        ev1.addPerson(p2);
        ev1.addExpense(ex1);
        return ev1;
    }
}
