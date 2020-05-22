import org.junit.Before;
import org.junit.Test;

import studentdatabase.*;

import java.util.Scanner;

import static org.junit.Assert.*;

public class MedStudentTest {
    private MedStudent medStudent;
    private String expected;

    @Before
    public void setUp() throws Exception {
        medStudent = new MedStudent(new Scanner("9821012,Jones,Mary,Chemistry Prize 1998"));
        expected = "Prize: Chemistry Prize 1998\n";
    }

    @Test
    public void testConstructor() {
        try {
            // we call this method with correct parameters
            medStudent = new MedStudent(new Scanner("1234567,Ann,Lisa"));
        }
        catch (Exception e) {
            fail("method should not fail");
        }

        try {
            // we call this method with correct parameters
            medStudent = new MedStudent(new Scanner("1234567,Ann,Lisa,Chemistry Prize 1"));
        }
        catch (Exception e) {
            fail("method should not fail");
        }

        try {
            // we call this method with correct parameters
            medStudent = new MedStudent(new Scanner("1234567,Ann,Lisa,Chemistry Prize 1,Physics Prize 1"));
        }
        catch (Exception e) {
            fail("method should not fail");
        }

        try {
            // we call this method with wrong parameters
            medStudent = new MedStudent(new Scanner("123ABC4,Ann,Lisa"));
            fail("method should fail");
        }
        catch (Exception e) {
            assertTrue(true);
        }

        try {
            // we call this method with wrong parameters
            medStudent = new MedStudent(new Scanner(",Ann,Lisa"));
            fail("method should fail");
        }
        catch (Exception e) {
            assertTrue(true);
        }

        try {
            // we call this method with wrong parameters
            medStudent = new MedStudent(new Scanner("12345678,Ann,Lisa"));
            fail("method should fail");
        }
        catch (Exception e) {
            assertTrue(true);
        }

        try {
            // we call this method with wrong parameters
            medStudent = new MedStudent(new Scanner("1234,Ann,Lisa"));
            fail("method should fail");
        }
        catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void testAddPrize() {
        String prize = "Neuroscience 1 Prize";

        // check prize does not exist
        assertFalse(medStudent.getPrizes().contains(prize));

        // add prize and assert it exists
        medStudent.addPrize(prize);
        assertTrue(medStudent.getPrizes().contains(prize));
    }

    @Test
    public void testWriteRecord() {
        assertEquals(expected, medStudent.writeRecord());
    }
}
