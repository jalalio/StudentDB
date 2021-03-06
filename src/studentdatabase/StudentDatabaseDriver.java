package studentdatabase;

import java.io.*;
import java.util.Scanner;

public class StudentDatabaseDriver implements Constants {

    public static void main(String[] args) throws Exception {

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a file name to process: ");
        String fileName = scan.nextLine().trim();
        File inputFileName = new File(fileName);
        BufferedReader fileReader = new BufferedReader(new FileReader(inputFileName));

//        String fileName = "sample1-extended.txt";
//        BufferedReader fileReader = new BufferedReader(new FileReader("data" + File.separator + fileName));

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

        // divert all System.out statements to output stream.
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        // print System.out statements as normal
        studentDB.printRecords();

        // reset to System.out stream.
        System.setOut(System.out);

        // output to a file
        String fileContent = output.toString();
        BufferedWriter writer = new BufferedWriter(new FileWriter("data/output.txt"));
        writer.write(fileContent);
        writer.close();
    }
}
