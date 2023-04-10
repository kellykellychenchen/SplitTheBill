package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Tests for the billbook class
public class BillBookTest {
    BillBook bb1;
    SpendingEvent spendingEvent1;
    SpendingEvent spendingEvent2;
    SpendingEvent spendingEvent3;

    @BeforeEach
    public void setup() {
        bb1 = new BillBook("test book");
        spendingEvent1 = new SpendingEvent("test event");
        spendingEvent2 = new SpendingEvent("second test event");
        spendingEvent3 = new SpendingEvent("third test event");
    }

    @Test
    public void testConstructor() {
        assertEquals("test book", bb1.getName());
        assertEquals(0,bb1.getEvents().size());
    }

    @Test
    public void testAddEvent() {
        bb1.addEvent(spendingEvent1);
        assertEquals(1,bb1.getEvents().size());
        assertEquals(spendingEvent1, bb1.getEvents().get(0));

        bb1.addEvent(spendingEvent2);
        assertEquals(2,bb1.getEvents().size());
        assertEquals(spendingEvent1, bb1.getEvents().get(0));
        assertEquals(spendingEvent2, bb1.getEvents().get(1));
    }

    @Test
    public void testGetUnmodifiableEvents() {
        bb1.addEvent(spendingEvent1);
        bb1.addEvent(spendingEvent2);
        assertEquals(2,bb1.getEvents().size());

        List<SpendingEvent> gottenList = bb1.getEvents();
        try {
            gottenList.add(spendingEvent3);
            gottenList.remove(spendingEvent1);
            fail("didn't catch exception when it should have");
        } catch (UnsupportedOperationException exception) {
            // good, proceed
        }
        assertEquals(2,gottenList.size());
        assertEquals(2,bb1.getEvents().size());
        assertEquals(spendingEvent1, bb1.getEvents().get(0));
        assertEquals(spendingEvent2, bb1.getEvents().get(1));
    }

    @Test
    public void testNumEvents() {
        assertEquals(0, bb1.numEvents());

        bb1.addEvent(spendingEvent1);
        assertEquals(1, bb1.numEvents());

        bb1.addEvent(spendingEvent2);
        assertEquals(2, bb1.numEvents());
    }

    @Test
    public void testRemoveEvent() {
        bb1.addEvent(spendingEvent1);
        bb1.addEvent(spendingEvent2);
        assertEquals(2,bb1.getEvents().size());

        bb1.removeEvent(spendingEvent1);
        assertEquals(1,bb1.getEvents().size());
        assertEquals(spendingEvent2, bb1.getEvents().get(0));
    }

    @Test
    public void testClearEvents() {
        bb1.addEvent(spendingEvent1);
        bb1.addEvent(spendingEvent2);
        assertEquals(2,bb1.getEvents().size());

        bb1.clearEvents();
        assertEquals(0,bb1.getEvents().size());
    }
}