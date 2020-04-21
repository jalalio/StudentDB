package studentdatabase;

import java.util.Scanner;

/**
 * A class that represents a Medicine Student
 */
public class MedStudent extends Student {
    private String[] prizes;

    public MedStudent(Scanner scan) {
        scan.useDelimiter(",");
        studentID = scan.next();
        familyName = scan.next();
        givenNames = scan.next();
        degree = "Medicine";
        results = new Result[NUMBER_OF_TOPICS];
        topicCount = 0;
        prizes = new String[NUMBER_OF_PRIZES];
        while(scan.hasNext())
            addPrize(scan.next());
    }

    public void addPrize(String s) {
        for (int i = 0; i < prizes.length; i++) {
            if(prizes[i] == null) {
                prizes[i] = s;
                break;
            }
        }
    }

    public String getPrizes() {
        String prizes = "";
        for(int i = 0; i < this.prizes.length; i++)
            if(this.prizes[i] != null)
                prizes += "Prize: " + this.prizes[i] + "\n";
        return prizes;
    }

    public String writeRecord() {
        return "Degree: "+degree+"\n"+getPrizes();
    }
}
