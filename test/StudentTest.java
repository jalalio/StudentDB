import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

public class StudentTest {
    private Student student;
    private String expected;

    @Before
    public void setUp() {
        student = new Student(new Scanner("9800123,Smith,John Paul"));
        student.addResult(new Scanner("COMP1000,PS,55"));
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
