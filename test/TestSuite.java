import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        StudentTest.class,
        ArtsStudentTest.class,
        MedStudentTest.class,
        StudentDatabaseTest.class,
        ResultTest.class,
        PrizeTest.class
})

public class TestSuite {}
