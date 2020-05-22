package studentdatabase;

import java.util.Scanner;

/**
 * A class to represent a Result
 */
public class Result {
    private String code;
    private String grade;
    private int mark;

    public Result(Scanner scan) throws Exception {
        scan.useDelimiter(",");
        code = scan.next();
        if(code.isEmpty()) {
            throw new Exception("Topic is not specified");
        }
        else if(code.length() != 8 ||
                !code.substring(0,4).matches("[A-Z]+") ||
                !code.substring(4).matches("[0-9]+")) {
            throw new Exception("Topic code is incorrect format");
        }
        grade = scan.next();
        if(grade.isEmpty()) {
            throw new Exception(" Grade is not specified");
        }
        else if(!grade.matches("FL") && !grade.matches("PS") && !grade.matches("CR") &&
                !grade.matches("DN") && !grade.matches("HD")) {
            throw new Exception("Topic grade is incorrect format");
        }
        if(scan.hasNextInt()) {
            mark = scan.nextInt();
            if(mark < 0 || mark > 100) {
                throw new Exception("Topic mark is incorrect");
            }
        }
        else if(scan.hasNext()) {
            throw new Exception("Topic mark is incorrect");
        }
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
