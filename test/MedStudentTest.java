import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

public class MedStudentTest {
    private MedStudent medStudent;
    private String expected;

    @Before
    public void setUp() {
        medStudent = new MedStudent(new Scanner("9821012,Jones,Mary,Chemistry Prize 1998"));
        expected = "Prize: Chemistry Prize 1998\n";
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
