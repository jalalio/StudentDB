import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import studentdatabase.*;

import java.io.*;
import java.util.Scanner;

import static org.junit.Assert.*;

public class StudentDatabaseTest {
    private StudentDatabase studentDB;
    private Scanner scan;
    private String fileName;
    private File inputFileName;
    private BufferedReader fileReader;

    @Before
    public void setUp() throws Exception {
        scan = new Scanner("data/sample2.txt");
        fileName = scan.nextLine().trim();
        inputFileName = new File(fileName);
        fileReader = new BufferedReader(new FileReader(inputFileName));

        studentDB = new StudentDatabase();
    }

    @Test
    public void testFileIO() {
        scan = new Scanner("data/sample1.txt");
        fileName = scan.nextLine().trim();
        inputFileName = new File(fileName);
        try {
            // we call this method with correct parameters
            fileReader = new BufferedReader(new FileReader(inputFileName));
        }
        catch (Exception e) {
            fail("method should not fail");
        }

        scan = new Scanner("data/nosuchfile.txt");
        fileName = scan.nextLine().trim();
        inputFileName = new File(fileName);
        try {
            // we call this method with wrong parameters
            fileReader = new BufferedReader(new FileReader(inputFileName));
            fail("method should fail");
        }
        catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void testAddStudent() throws Exception {
        init();

        assertNotNull(studentDB.findStudent("9800123"));
        assertNotNull(studentDB.findStudent("9821010"));
        assertNotNull(studentDB.findStudent("9987654"));
        assertNull(studentDB.findStudent("1234567"));
        assertNull(studentDB.findStudent("7654321"));
    }

    @Test
    public void testFindStudent() throws Exception {
        assertNull(studentDB.findStudent("9800124"));
        studentDB.addStudent("S,9800124,Mooney,Carl");
        assertNotNull(studentDB.findStudent("9800124"));
    }

    @Test
    public void testAddResult() throws Exception {
        studentDB.addStudent("S,9800124,Mooney,Carl");
        assertFalse(studentDB.findStudent("9800124").writeResults().contains("ENGR3791"));
        studentDB.addResult("R,9800124,ENGR3791,HD,100");
        assertTrue(studentDB.findStudent("9800124").writeResults().contains("ENGR3791"));
    }

    @Test
    public void testAwardPrize() throws Exception {
        init();

        studentDB.awardPrize("Neuroscience 1 Prize","MMED1904",1);
        assertFalse(studentDB.findStudent("9800123").writeRecord().contains("Neuroscience 1 Prize"));
        assertFalse(studentDB.findStudent("9821010").writeRecord().contains("Neuroscience 1 Prize"));
        assertTrue(studentDB.findStudent("9821011").writeRecord().contains("Neuroscience 1 Prize"));
        assertFalse(studentDB.findStudent("9987654").writeRecord().contains("Neuroscience 1 Prize"));
    }

    @Test
    public void testPrintRecords() throws Exception {
        init();

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
    public void testClearRecords() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        init();

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

    public void init() throws Exception {
        String s;
        while ((s = fileReader.readLine()) != null) {
            if (s.length() > 0)
                switch (s.charAt(0)) {
                    case 'A':
                    case 'M':
                    case 'S':
                        studentDB.addStudent(s);
                        break;
                    case 'R':
                        studentDB.addResult(s);
                        break;
                    case 'P':
                    default:
                        break;
                }
        }
    }
}
