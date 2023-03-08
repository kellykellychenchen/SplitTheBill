package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
//TODO: add tests for more fields in constructor
// Tests for methods in the person class.
public class PersonTest {
    Person p1;

    @BeforeEach
    public void setUp() {
        p1 = new Person("name1");
    }

    @Test
    //TODO: update constructor test
    public void testConstructor() {
        assertEquals("name1", p1.getName());
    }


}
