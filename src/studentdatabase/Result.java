package studentdatabase;

import java.util.Scanner;

/**
 * A class to represent a Result
 */
public class Result {
    // todo your code goes here
    private String code;
    private String grade;
    private int mark;

    public Result(Scanner scan) {
        scan.useDelimiter(",");
        code = scan.next();
        grade = scan.next();
        if(scan.hasNextInt())
            mark = scan.nextInt();
        else
            mark = -1;
    }

    public String getCode() {
        return code;
    }

    public String getGrade() {
        return grade;
    }

    public int getMark() {
        return mark;
    }

    public String writeRecord() {
        if(mark != -1)
            return code+" "+grade+" "+mark;
        return code+" "+grade;
    }
}
