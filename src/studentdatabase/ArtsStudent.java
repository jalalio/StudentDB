package studentdatabase;

import java.util.Scanner;

/**
 * A class that represents an Arts Student
 */
public class ArtsStudent extends Student {
    private String major;
    private String minor;

    public ArtsStudent(Scanner scan) throws Exception {
        scan.useDelimiter(",");
        studentID = scan.next();
        if(!studentID.matches("[0-9]+") || studentID.length() != 7) {
            throw new Exception("StudentID: "+studentID+" Error in Student ID");
        }
        familyName = scan.next();
        if(familyName.isEmpty()) {
            throw new Exception("StudentID: "+studentID+" Family Name is Not Given");
        }
        givenNames = scan.next();
        if(givenNames.isEmpty()) {
            throw new Exception("StudentID: "+studentID+" Full Name is Not Given");
        }
        degree = "Arts";
        results = new Result[NUMBER_OF_TOPICS];
        topicCount = 0;
        major = scan.next();
        minor = scan.next();
        if(scan.hasNext()) {
            throw new Exception("StudentID: "+studentID+" Major/Minor error");
        }
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

