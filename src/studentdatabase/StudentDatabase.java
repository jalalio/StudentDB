package studentdatabase;

import java.util.*;

/**
 * A class for storing information about Students
 */
public class StudentDatabase implements Constants {
    Student[] db;
    int studentCount;

    public StudentDatabase() {
        db = new Student[NUMBER_OF_STUDENTS];
        studentCount = 0;
    }

    public void addStudent(String s) {
        Scanner scan = new Scanner(s);
        scan.useDelimiter(",");
        switch (scan.next().charAt(0)) {
            case 'A':
                db[studentCount++] = new ArtsStudent(scan);
                break;
            case 'M':
                db[studentCount++] = new MedStudent(scan);
                break;
            case 'S':
                db[studentCount++] = new Student(scan);
                break;
        }
    }

    public Student findStudent(String ID) {
        for (int i = 0; i < studentCount; i++) {
            if(db[i].getID().equals(ID))
                return db[i];
        }
        return null;
    }

    public void addResult(String s) {
        Scanner scan = new Scanner(s);
        scan.useDelimiter(",");
        scan.next(); // R
        findStudent(scan.next()).addResult(scan);
    }

    public void awardPrize(String prize, String template, int topicsRequired) {
        List<Integer> individualTopicMarks = new ArrayList<>();
        int studentAverageMark = 0;
        int highestMark = 0;
        MedStudent studentWithHighestMark = null;
        for (int i = 0; i < studentCount; i++) {
            if(db[i].getDegree().equals("Medicine")) { // find a medical student
                for(int j = 0; j < db[i].topicCount; j++) {
                    if(db[i].getCode(j).contains(template)) // match topic
                        individualTopicMarks.add(db[i].getMark(j)); // add mark
                }
                if(!individualTopicMarks.isEmpty()) {
                    Collections.sort(individualTopicMarks, Collections.reverseOrder()); // sort marks from highest
                    for (int j = 0; j < individualTopicMarks.size() && j < topicsRequired; j++)
                        studentAverageMark += individualTopicMarks.get(j); // tally highest average marks
                    studentAverageMark = studentAverageMark / individualTopicMarks.size(); // get average mark
                    if (studentAverageMark > highestMark)
                        studentWithHighestMark = (MedStudent) db[i];
                }
            }
        }
        if(studentWithHighestMark != null)
            studentWithHighestMark.addPrize(prize);
    }

    public void printRecords() {
        for (int i = 0; i < studentCount; i++) {
            System.out.print(db[i].writeHeader());
            System.out.print(db[i].writeRecord());
            System.out.print(db[i].writeResults());
            System.out.println();
        }
    }

    public void clearRecords() {
        for (; studentCount > 0; )
            db[--studentCount] = null;
    }

}
