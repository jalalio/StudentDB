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
        Student student;
        List<Integer> marks = new ArrayList<>();
        int averageMark = 0;
        int highestMark = 0;
        MedStudent studentWithHighestMark = null;
        for (int i = 0; i < studentCount; i++) {
            student = db[i];
            if(student.getDegree().equals("Medicine")) { // find a medical student
                for(int j = 0; j < student.topicCount; j++) {
                    if(student.getCode(j).contains(template)) // check for matching topic code(s)
                        marks.add(student.getMark(j)); // tally mark(s)
                }
                if(marks.size() >= topicsRequired) { // check min topics required achieved
                    Collections.sort(marks, Collections.reverseOrder()); // sort marks from highest
                    for (int k = 0; k < topicsRequired; k++)
                        averageMark += marks.get(k); // tally highest marks achieved in required topic(s)
                    averageMark = averageMark / topicsRequired; // get highest average mark
                    if (averageMark > highestMark) { // check if average mark is higher than previous students
                        studentWithHighestMark = (MedStudent) student; // update the student with the highest mark
                        highestMark = averageMark; // update the highest mark
                    }
                    averageMark = 0;
                }
                marks.clear();
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
