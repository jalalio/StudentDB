import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

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
    public void result() {
        assertEquals(expected, new Result(value).writeRecord());
    }
}
