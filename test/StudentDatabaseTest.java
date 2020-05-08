import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class StudentDatabaseTest {
    private StudentDatabase studentDB;

    @Before
    public void setUp() {
        studentDB = new StudentDatabase();
        studentDB = new StudentDatabase();
        studentDB.addStudent("S,9800123,Smith,John Paul"); // Science student
        studentDB.addResult("R,9800123,BIOL1000,HD,89");
        studentDB.addStudent("M,9821010,Sanders,Sam"); // Medicine student
        studentDB.addResult("R,9821010,MMED1000,DN,90");
        studentDB.addResult("R,9821010,PSYC1001,DN,95");
        studentDB.addStudent("M,9821011,Spurrier,Nicola"); // Medicine student
        studentDB.addResult("R,9821011,MMED1000,HD,88");
        studentDB.addResult("R,9821011,MMED1904,DN,84");
        studentDB.addStudent("M,9821012,Jones,Mary,Chemistry Prize 1998"); // Medicine student
        studentDB.addResult("R,9821012,MMED1001,HD,97");
        studentDB.addStudent("M,9821013,Joe,Lazy"); // Medicine student
        studentDB.addStudent("M,9821014,Michael,Average"); // Medicine student
        studentDB.addResult("R,9821014,MMED1000,PS,55");
        studentDB.addResult("R,9821014,MMED1001,PS,50");
        studentDB.addResult("R,9821014,MMED1002,CR,65");
        studentDB.addStudent("A,9987654,Howard,John,Politics,Economics"); // Arts student
    }

    @Test
    public void testAddStudent() {
        assertNull(studentDB.findStudent("9800124"));
        studentDB.addStudent("S,9800124,Mooney,Carl");
        assertNotNull(studentDB.findStudent("9800124"));
    }

    @Test
    public void testFindStudent() {
        studentDB.addStudent("S,9800124,Mooney,Carl");
        assertNull(studentDB.findStudent("9800000"));
        assertNotNull(studentDB.findStudent("9800124"));
    }

    @Test
    public void testAddResult() {
        studentDB.addStudent("S,9800124,Mooney,Carl");
        assertFalse(studentDB.findStudent("9800124").writeResults().contains("ENGR3791"));
        studentDB.addResult("R,9800124,ENGR3791,HD,100");
        assertTrue(studentDB.findStudent("9800124").writeResults().contains("ENGR3791"));
    }

    @Test
    public void testAwardPrize() {
        studentDB.awardPrize("Neuroscience 1 Prize","MMED1904",1);
        assertFalse(studentDB.findStudent("9800123").writeRecord().contains("Neuroscience 1 Prize"));
        assertFalse(studentDB.findStudent("9821010").writeRecord().contains("Neuroscience 1 Prize"));
        assertTrue(studentDB.findStudent("9821011").writeRecord().contains("Neuroscience 1 Prize"));
        assertFalse(studentDB.findStudent("9987654").writeRecord().contains("Neuroscience 1 Prize"));
    }

    @Test
    public void testPrintRecords() {
        // divert all System.out statements to output stream.
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        // print System.out statements as normal
        studentDB.printRecords();

        String expectedOutputSample  = "Academic record for John Paul Smith (9800123)\n" +
                "Degree: Science\n" +
                "BIOL1000 HD 89";
        String falseOutputSample = "Academic record for John Paul Smith (9800120)\n" +
                "Degree: Science\n" +
                "BIOL1000 HD 89";

        assertTrue(output.toString().contains(expectedOutputSample));
        assertFalse(output.toString().contains(falseOutputSample));

        // reset to System.out stream.
        System.setOut(System.out);
    }

    @Test
    public void testClearRecords() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        // assert that student records exist
        studentDB.printRecords();
        assertFalse(output.toString().isEmpty());

        // reset the output stream to zero
        output.reset();

        // call clearRecords method and assert that student records no longer exist
        studentDB.clearRecords();
        studentDB.printRecords();
        assertTrue(output.toString().isEmpty());

        // reset to System.out stream.
        System.setOut(System.out);
    }

    @After
    public void tearDown() {
        studentDB.clearRecords();
    }
}
