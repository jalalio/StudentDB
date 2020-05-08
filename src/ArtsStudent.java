import java.util.Scanner;

/**
 * A class that represents an Arts Student
 */
public class ArtsStudent extends Student {
    private String major;
    private String minor;

    public ArtsStudent(Scanner scan) {
        scan.useDelimiter(",");
        studentID = scan.next();
        familyName = scan.next();
        givenNames = scan.next();
        degree = "Arts";
        results = new Result[NUMBER_OF_TOPICS];
        topicCount = 0;
        major = scan.next();
        minor = scan.next();
    }

    public String getMajor() {
        return major;
    }

    public String getMinor() {
        return minor;
    }

    public String writeRecord() {
        return "Major: "+major+"\nMinor: "+minor+"\n";
    }
}

