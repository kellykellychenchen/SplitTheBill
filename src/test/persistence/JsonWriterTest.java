package persistence;

import model.BillBook;
import model.Event;
import model.Expense;
import model.Person;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Tests for JsonWriter
public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            BillBook bb = new BillBook("test billbook");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // do nothing, test passes
        }
    }

    @Test
    void testWriterEmptyBillBook() {
        try {
            BillBook bb = new BillBook("test billbook");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyBillBook.json");
            writer.open();
            writer.write(bb);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyBillBook.json");
            bb = reader.read();
            assertEquals("test billbook", bb.getName());
            assertEquals(0, bb.numEvents());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralBillBook() {
        try {
            BillBook bb = new BillBook("test billbook");
            Event ev1 = setUpEvent1();
            Event ev2 = new Event("empty event");
            bb.addEvent(ev1);
            bb.addEvent(ev2);

            bb = getBillBook(bb);

            // test billbook equals
            assertEquals("test billbook", bb.getName());

            // test events equals
            List<Event> events = bb.getEvents();
            assertEquals(2, events.size());

            checkEvent(events.get(0), ev1.getEventName(), ev1.getPeople(), ev1.getExpenses());
            checkEvent(events.get(1), ev2.getEventName(), ev2.getPeople(), ev2.getExpenses());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // EFFECTS: set up a test event ev1 and returns it
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

    // EFFECTS: writes the given BillBook bb to file and read it back in from file and returns it.
    private BillBook getBillBook(BillBook bb) throws IOException {
        JsonWriter writer = new JsonWriter("./data/testWriterGeneralBillBook.json");
        writer.open();
        writer.write(bb);
        writer.close();
        JsonReader reader = new JsonReader("./data/testWriterGeneralBillBook.json");
        bb = reader.read();
        return bb;
    }
}
