import org.junit.Before;
import org.junit.Test;

import studentdatabase.*;

import java.util.Scanner;

import static org.junit.Assert.*;

public class ArtsStudentTest {
    private ArtsStudent artsStudent;
    private String expected;

    @Before
    public void setUp() throws Exception {
        artsStudent = new ArtsStudent(new Scanner("9987654,Howard,John,Politics,Economics"));
        expected = "Major: Politics\n" + "Minor: Economics\n";
    }

    @Test
    public void testConstructor() {
        try {
            // we call this method with correct parameters
            artsStudent = new ArtsStudent(new Scanner("1234567,Ann,Emma,Politics,Economics"));
        }
        catch (Exception e) {
            fail("method should not fail");
        }

        try {
            // we call this method with wrong parameters
            artsStudent = new ArtsStudent(new Scanner("1234567,Ann,Lisa,Politics"));
            fail("method should fail");
        }
        catch (Exception e) {
            assertTrue(true);
        }

        try {
            // we call this method with wrong parameters
            artsStudent = new ArtsStudent(new Scanner("1234567,Ann,Lisa,Politics,Economics,Maths"));
            fail("method should fail");
        }
        catch (Exception e) {
            assertTrue(true);
        }

        try {
            // we call this method with wrong parameters
            artsStudent = new ArtsStudent(new Scanner("1234567,Ann,Lisa,"));
            fail("method should fail");
        }
        catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void testWriteRecord() {
        assertEquals(expected, artsStudent.writeRecord());
    }
}
