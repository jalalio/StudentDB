import org.junit.Before;
import org.junit.Test;
import studentdatabase.*;

import java.util.Scanner;

import static org.junit.Assert.*;

public class StudentTest {
    private Student student;
    private String expected;

    @Before
    public void setUp() throws Exception {
        student = new Student(new Scanner("9800123,Smith,John Paul"));
        student.addResult(new Scanner("COMP1000,PS,55"));
    }

    @Test
    public void testConstructor() {
        try {
            // we call this method with correct parameters
            student = new Student(new Scanner("1234567,Ann,Lisa"));
        }
        catch (Exception e) {
            fail("method should not fail");
        }

        try {
            // we call this method with wrong parameters
            student = new Student(new Scanner("123ABC4,Ann,Lisa"));
            fail("method should fail");
        }
        catch (Exception e) {
            assertTrue(true);
        }

        try {
            // we call this method with wrong parameters
            student = new Student(new Scanner(",Ann,Lisa"));
            fail("method should fail");
        }
        catch (Exception e) {
            assertTrue(true);
        }

        try {
            // we call this method with wrong parameters
            student = new Student(new Scanner("12345678,Ann,Lisa"));
            fail("method should fail");
        }
        catch (Exception e) {
            assertTrue(true);
        }

        try {
            // we call this method with wrong parameters
            student = new Student(new Scanner("1234,Ann,Lisa"));
            fail("method should fail");
        }
        catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void testWriteHeader() {
        expected = "Academic record for John Paul Smith (9800123)\n" + "Degree: Science\n";
        assertEquals(expected, student.writeHeader());
    }

    @Test
    public void testWriteResults() {
        expected = "COMP1000 PS 55\n";
        assertEquals(expected, student.writeResults());
    }

    @Test
    public void testWriteRecord() {
        assertTrue(student.writeRecord().isEmpty());
    }
}
