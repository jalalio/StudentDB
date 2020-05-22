import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import studentdatabase.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

import static org.junit.Assert.*;

@RunWith(value = Parameterized.class)
public class ResultTest {
    private String expected;
    private Scanner value;

    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][] {{"BIOL1000 HD 89", "BIOL1000,HD,89"}, {"CHEM1001 HD", "CHEM1001,HD"},
                {"COMP1000 PS 55", "COMP1000,PS,55"}, {"COMP1000 DN 75", "COMP1000,DN,75"}, {"HIST1234 HD", "HIST1234,HD"}});
    }

    public ResultTest(String expected, String value) {
        this.expected = expected;
        this.value = new Scanner(value);
    }

    @Test
    public void testConstructor() {
        try {
            // we call this method with correct parameters
            new Result(new Scanner("HIST1234,HD"));
        }
        catch (Exception e) {
            fail("method should not fail");
        }

        try {
            // we call this method with correct parameters
            new Result(new Scanner("COMP2008,DN,89"));
        }
        catch (Exception e) {
            fail("method should not fail");
        }
    }

    @Test
    public void testConstructorTopicCode() {
        try {
            // we call this method with wrong parameters
            new Result(new Scanner(",DN,89"));
            fail("method should fail");
        }
        catch (Exception e) {
            assertTrue(true);
        }

        try {
            // we call this method with wrong parameters
            new Result(new Scanner("COMP,DN,89"));
            fail("method should fail");
        }
        catch (Exception e) {
            assertTrue(true);
        }

        try {
            // we call this method with wrong parameters
            new Result(new Scanner("COMP2008X,DN,89"));
            fail("method should fail");
        }
        catch (Exception e) {
            assertTrue(true);
        }

        try {
            // we call this method with wrong parameters
            new Result(new Scanner("2008COMP,DN,89"));
            fail("method should fail");
        }
        catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void testConstructorTopicGrade() {
        try {
            // we call this method with wrong parameters
            new Result(new Scanner("COMP2020,,49"));
            fail("method should fail");
        }
        catch (Exception e) {
            assertTrue(true);
        }

        try {
            // we call this method with wrong parameters
            new Result(new Scanner("COMP2020,F"));
            fail("method should fail");
        }
        catch (Exception e) {
            assertTrue(true);
        }

        try {
            // we call this method with wrong parameters
            new Result(new Scanner("COMP2020,FLX"));
            fail("method should fail");
        }
        catch (Exception e) {
            assertTrue(true);
        }

        try {
            // we call this method with wrong parameters
            new Result(new Scanner("COMP2020,A6"));
            fail("method should fail");
        }
        catch (Exception e) {
            assertTrue(true);
        }

        try {
            // we call this method with wrong parameters
            new Result(new Scanner("COMP2020,fl"));
            fail("method should fail");
        }
        catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void testConstructorTopicMark() {
        try {
            // we call this method with wrong parameters
            new Result(new Scanner("COMP2021,PS,-1"));
            fail("method should fail");
        }
        catch (Exception e) {
            assertTrue(true);
        }

        try {
            // we call this method with wrong parameters
            new Result(new Scanner("COMP2021,PS,101"));
            fail("method should fail");
        }
        catch (Exception e) {
            assertTrue(true);
        }

        try {
            // we call this method with wrong parameters
            new Result(new Scanner("COMP2021,PS,XX"));
            fail("method should fail");
        }
        catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void result() throws Exception {
        assertEquals(expected, new Result(value).writeRecord());
    }
}
