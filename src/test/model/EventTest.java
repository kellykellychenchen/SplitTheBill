package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Event class
 */
public class EventTest {
	private Event e;
    private Event e1;
    private Event e2;
	private Date d;
	
	//NOTE: these tests might fail if time at which line (2) below is executed
	//is different from time that line (1) is executed.  Lines (1) and (2) must
	//run in same millisecond for this test to make sense and pass.
	
	@BeforeEach
	public void runBefore() {
		e = new Event("Sensor open at door");   // (1)
        e2 = new Event("same time different description");
		d = Calendar.getInstance().getTime();   // (2)
	}
	
	@Test
	public void testEvent() {
		assertEquals("Sensor open at door", e.getDescription());
        assertTrue(-2 < e.getDate().compareTo(d) && 2 > e.getDate().compareTo(d));
	}

	@Test
	public void testToString() {
		assertEquals(d.toString() + "\n" + "Sensor open at door", e.toString());
	}

    @Test
    public void testEquals() {
        Event e4 = null;
        assertNotEquals(e, e4);

        String s = "string";
        assertNotEquals(e, s);

        e1 = e;
        assertEquals(e, e1);

        assertNotEquals(e, e2);

        Event e3 = new Event("diff time diff description");
        assertNotEquals(e, e3);

        Event e5 = new Event("Sensor open at door");
        e5.getDate().setTime(100000);
        assertNotEquals(e, e5);
    }

    @Test
    public void testHashCode() {
        assertNotEquals(e2.hashCode(), e.hashCode());
    }
}
