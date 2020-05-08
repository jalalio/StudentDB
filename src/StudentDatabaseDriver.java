import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class StudentDatabaseDriver implements Constants {

    public static void main(String[] args) throws IOException {
        /*
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a file name to process: ");
        String fileName = scan.nextLine().trim();
        File inputFileName = new File(fileName);
        BufferedReader fileReader = new BufferedReader(new FileReader(inputFileName));
         */
        String fileName = "input.txt";
        BufferedReader fileReader = new BufferedReader(new FileReader("data" + File.separator + fileName));

        String s;
        int prizeCount = 0;
        StudentDatabase studentDB = new StudentDatabase();
        Prize[] prizeList = new Prize[NUMBER_OF_PRIZES];
        while ((s = fileReader.readLine()) != null) {
            if (s.length() > 0)
                switch (s.charAt(0)) {
                    case 'A':
                    case 'M':
                    case 'S':
                        studentDB.addStudent(s);
                        break;
                    case 'R':
                        studentDB.addResult(s);
                        break;
                    case 'P':
                        prizeList[prizeCount++] = new Prize(s);
                        break;
                    default:
                        break;
                } // switch
        } // while
        for (int i = 0; i < prizeCount; i++)
            prizeList[i].awardPrize(studentDB);
        studentDB.printRecords();
    }
}
