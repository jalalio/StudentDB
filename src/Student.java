import java.util.Scanner;

/**
 * A class that represents a Student
 */
public class Student implements Constants {
    protected String studentID;
    protected String familyName;
    protected String givenNames;
    protected String degree;
    protected Result[] results;
    protected int topicCount;

    public Student() {} // empty constructor

    public Student(Scanner scan) {
        scan.useDelimiter(",");
        studentID = scan.next();
        familyName = scan.next();
        givenNames = scan.next();
        degree = "Science";
        results = new Result[NUMBER_OF_TOPICS];
        topicCount = 0;
    }

    public String getID() {
        return studentID;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getGivenNames() {
        return givenNames;
    }

    public String getDegree() {
        return degree;
    }

    public int getIndex(String s) {
        return -1;
    }

    public String getCode(int i) {
        return results[i].getCode();
    }

    public String getGrade(int i) {
        return results[i].getGrade();
    }

    public int getMark(int i) {
        return results[i].getMark();
    }

    public int getTopicCount() {
        return topicCount;
    }

    public void addResult(Scanner scan) {
        results[topicCount++] = new Result(scan);
    }

    public String writeHeader() {
        return "Academic record for "+givenNames+" "+familyName+" ("+studentID+")\n"+"Degree: "+degree+"\n";
    }

    public String writeResults() {
        String results = "";
        for (int i = 0; i < topicCount; i++) {
            results += this.results[i].writeRecord() + "\n";
        }
        return results;
    }

    public String writeRecord() {
        return "";
    }
}
