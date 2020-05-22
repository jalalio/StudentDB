import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import studentdatabase.*;

import static org.junit.Assert.*;

public class PrizeTest {
    private Prize prize;
    private StudentDatabase studentDB;

    @Before
    public void setUp() throws Exception {
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

        prize = new Prize("P,Medicine 1 prize,MMED1,2");
    }

    @Test
    public void testAwardPrize() {
        prize.awardPrize(studentDB);
        assertFalse(studentDB.findStudent("9800123").writeRecord().contains("Medicine 1 prize"));
        assertFalse(studentDB.findStudent("9821010").writeRecord().contains("Medicine 1 prize"));
        assertTrue(studentDB.findStudent("9821011").writeRecord().contains("Medicine 1 prize"));
        assertFalse(studentDB.findStudent("9821012").writeRecord().contains("Medicine 1 prize"));
        assertFalse(studentDB.findStudent("9821013").writeRecord().contains("Medicine 1 prize"));
        assertFalse(studentDB.findStudent("9821014").writeRecord().contains("Medicine 1 prize"));
        assertFalse(studentDB.findStudent("9987654").writeRecord().contains("Medicine 1 prize"));
    }

    @After
    public void tearDown() {
        studentDB.clearRecords();
    }
}
