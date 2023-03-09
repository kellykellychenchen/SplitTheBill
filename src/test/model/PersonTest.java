package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Tests for the person class.
public class PersonTest {
    Person p1;

    @BeforeEach
    public void setUp() {
        p1 = new Person("name1");
    }

    @Test
    public void testConstructor() {
        assertEquals("name1", p1.getName());
        assertEquals(0, p1.getTotalPaid());
        assertEquals(0, p1.getTotalShared());
        assertEquals(0, p1.getBalance());
    }

    @Test
    public void testSetName() {
        p1.setName("not name1");
        assertEquals("not name1", p1.getName());
    }

    @Test
    public void testSetTotalPaid() {
        p1.setTotalPaid(200);
        assertEquals(200, p1.getTotalPaid());
    }

    @Test
    public void testSetTotalShared() {
        p1.setTotalShared(50);
        assertEquals(50, p1.getTotalShared());
    }

    @Test
    public void testSetBalance() {
        p1.setBalance(20);
        assertEquals(20, p1.getBalance());
    }
}
