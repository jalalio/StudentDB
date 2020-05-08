import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

public class ArtsStudentTest {
    private ArtsStudent artsStudent;
    private String expected;

    @Before
    public void setUp() {
        artsStudent = new ArtsStudent(new Scanner("9987654,Howard,John,Politics,Economics"));
        expected = "Major: Politics\n" + "Minor: Economics\n";
    }

    @Test
    public void testWriteRecord() {
        assertEquals(expected, artsStudent.writeRecord());
    }
}
