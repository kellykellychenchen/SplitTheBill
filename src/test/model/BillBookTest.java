package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Tests for the billbook class
public class BillBookTest {
    BillBook bb1;
    Event event1;
    Event event2;
    Event event3;

    @BeforeEach
    public void setup() {
        bb1 = new BillBook("test book");
        event1 = new Event("test event");
        event2 = new Event("second test event");
        event3 = new Event("third test event");
    }

    @Test
    public void testConstructor() {
        assertEquals("test book", bb1.getName());
        assertEquals(0,bb1.getEvents().size());
    }

    @Test
    public void testAddEvent() {
        bb1.addEvent(event1);
        assertEquals(1,bb1.getEvents().size());
        assertEquals(event1, bb1.getEvents().get(0));

        bb1.addEvent(event2);
        assertEquals(2,bb1.getEvents().size());
        assertEquals(event1, bb1.getEvents().get(0));
        assertEquals(event2, bb1.getEvents().get(1));
    }

    @Test
    public void testGetUnmodifiableEvents() {
        bb1.addEvent(event1);
        bb1.addEvent(event2);
        assertEquals(2,bb1.getEvents().size());

        List<Event> gottenList = bb1.getEvents();
        try {
            gottenList.add(event3);
            gottenList.remove(event1);
            fail("didn't catch exception when it should have");
        } catch (UnsupportedOperationException exception) {
            // good, proceed
        }
        assertEquals(2,gottenList.size());
        assertEquals(2,bb1.getEvents().size());
        assertEquals(event1, bb1.getEvents().get(0));
        assertEquals(event2, bb1.getEvents().get(1));
    }

    @Test
    public void testNumEvents() {
        assertEquals(0, bb1.numEvents());

        bb1.addEvent(event1);
        assertEquals(1, bb1.numEvents());

        bb1.addEvent(event2);
        assertEquals(2, bb1.numEvents());
    }
}