package studentdatabase;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class StudentDatabaseGUI extends JFrame implements ActionListener, KeyListener {
    private JPanel mainPanel;
    private JLabel headerLabel;
    private JPanel studentDetailsPanel;
    private JPanel topicDetailsPanel;
    private JTextField givenNaField;
    private JComboBox gradeBox;
    private JButton addTopicResultButton;
    private JButton findTopicResultButton;
    private JComboBox degreeBox;
    private JButton addStudentButton;
    private JButton findStudentButton;
    private JButton printAllRecordsButton;
    private JButton clearAllRecordsButton;
    private JPanel degreeOptionsPane;
    private JPanel awardPrizesPane;
    private JTextArea medPrizeField;
    private JLabel studentLabel;
    private JLabel familyLabel;
    private JLabel givenLabel;
    private JLabel degreeLabel;
    private JLabel artsMaLabel;
    private JLabel artsMiLabel;
    private JLabel medLabel;
    private JLabel topicLabel;
    private JLabel gradeLabel;
    private JLabel markLabel;
    private JLabel prizeLabel;
    private JLabel templateLabel;
    private JLabel noTopicLabel;
    private JButton awardPrizeButton;
    private JTextField prizeField;
    private JTextField templateField;
    private JTextField noTopicsField;
    private JTextField artsMinorField;
    private JTextField artsMajorField;
    private JTextField studentNoField;
    private JTextField familyNaField;
    private JTextField topicCodeField;
    private JTextField markField;
    private JLabel authorLabel;
    private JPanel studentPane;
    private JPanel recordsPane;
    private JPanel statusPane;
    private JLabel statusLabel;

    private ImageIcon errorIcon;
    private ImageIcon successIcon;
    private StudentDatabase studentDB;
    private static boolean nimbus;

    public StudentDatabaseGUI(String title) {
        super(title);

        // create a new student database
        studentDB = new StudentDatabase();

        try {
            // upload system icons
            BufferedImage inputImage;
            inputImage = ImageIO.read(new File("data"+File.separator+"images"+File.separator+"error.png"));
            errorIcon = new ImageIcon(inputImage.getScaledInstance(15, 15, Image.SCALE_FAST));
            inputImage = ImageIO.read(new File("data"+File.separator+"images"+File.separator+"success.png"));
            successIcon = new ImageIcon(inputImage.getScaledInstance(15, 15, Image.SCALE_FAST));
        } catch (IOException ex) {
            System.err.println("Icon not found.");
            ex.printStackTrace();
        }

        // set up degree choices
        degreeBox.setFont(new Font("", Font.PLAIN, 12));
        degreeBox.addItem("Science");
        degreeBox.addItem("Medicine");
        degreeBox.addItem("Arts");

        // set up grade choices
        gradeBox.setFont(new Font("", Font.PLAIN, 12));
        gradeBox.addItem("HD");
        gradeBox.addItem("DN");
        gradeBox.addItem("CR");
        gradeBox.addItem("PS");
        gradeBox.addItem("FL");

        // set up title font
        headerLabel.setFont(new Font("", Font.BOLD, 20));

//        medPrizeField.setLineWrap(true);
        mainPanel.setPreferredSize(new Dimension(700,450));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        // Add Action Listeners
        degreeBox.addActionListener(this);
        addStudentButton.addActionListener(this);
        findStudentButton.addActionListener(this);
        addTopicResultButton.addActionListener(this);
        findTopicResultButton.addActionListener(this);
        awardPrizeButton.addActionListener(this);
        printAllRecordsButton.addActionListener(this);
        clearAllRecordsButton.addActionListener(this);

        // Add Key Listeners
        studentNoField.addKeyListener(this);
        familyNaField.addKeyListener(this);
        givenNaField.addKeyListener(this);
        medPrizeField.addKeyListener(this);
        artsMajorField.addKeyListener(this);
        artsMinorField.addKeyListener(this);
        topicCodeField.addKeyListener(this);
        markField.addKeyListener(this);
        prizeField.addKeyListener(this);
        templateField.addKeyListener(this);
        noTopicsField.addKeyListener(this);
    }

    public static void main (String[] args) {
        // Theme
        initLookAndFeel();

        // Frame
        JFrame frame = new StudentDatabaseGUI("Student Database Program");
        frame.setResizable(false);
        frame.setVisible(true);

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu file = new JMenu("File");
        menuBar.add(file);

        JMenuItem exit = new JMenuItem("Exit");
        file.add(exit);

        class ExitAction implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }
        exit.addActionListener(new ExitAction());
    }

    private static void initLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    nimbus = true;
                    UIManager.setLookAndFeel(info.getClassName());
//                    SwingUtilities.updateComponentTreeUI(this);
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, GUI set to default look and feel.
            nimbus = false;
            System.err.println("Using the default look and feel.");
            e.printStackTrace();
        }
    }

    private void clearStudentDetails() {
        studentNoField.setText(null);
        familyNaField.setText(null);
        givenNaField.setText(null);
        degreeBox.setSelectedItem("Science");
        medPrizeField.setText(null);
        artsMajorField.setText(null);
        artsMinorField.setText(null);
        addStudentButton.setText("Add Student");
    }

    private void clearTopicDetails() {
        topicCodeField.setText(null);
        gradeBox.setSelectedItem("HD");
        markField.setText(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Enter New Student")) {
            clearStudentDetails();
            statusLabel.setIcon(null);
            statusLabel.setText("");
            return;
        }
        if(e.getActionCommand().equals("comboBoxChanged")) {
            JComboBox cb = (JComboBox) e.getSource();
            String degreeName = (String) cb.getSelectedItem();
            if(degreeName.equals("Science")) {
                artsMajorField.setText(null);
                artsMajorField.setEnabled(false);
                artsMinorField.setText(null);
                artsMinorField.setEnabled(false);
                medPrizeField.setText(null);
                medPrizeField.setEnabled(false);
            }
            else if(degreeName.equals("Medicine")) {
                artsMajorField.setText(null);
                artsMajorField.setEnabled(false);
                artsMinorField.setText(null);
                artsMinorField.setEnabled(false);
                medPrizeField.setEnabled(true);
            }
            else { // if(degreeName.equals("Arts")) {
                artsMajorField.setEnabled(true);
                artsMinorField.setEnabled(true);
                medPrizeField.setText(null);
                medPrizeField.setEnabled(false);
            }
        }
        if(e.getActionCommand().equals("Add Student")) {
            String studentID = studentNoField.getText();
            String familyName = familyNaField.getText();
            String givenNames = givenNaField.getText();
            String degree = degreeBox.getSelectedItem().toString();
            String prizes = medPrizeField.getText();
            String major = artsMajorField.getText();
            String minor = artsMinorField.getText();

            if(studentID.length() != 7 || !studentID.matches("[0-9]+")) {
                studentNoField.requestFocus();
                statusLabel.setIcon(errorIcon);
                statusLabel.setText("WARNING: Student Number entered is invalid!");
            }
            else if(studentDB.findStudent(studentID) != null) {
                studentNoField.requestFocus();
                statusLabel.setIcon(errorIcon);
                statusLabel.setText("WARNING: Student Number entered already exists!");
            }
            else if(familyName.isBlank() || !familyName.matches("[a-zA-Z]+")) {
                familyNaField.requestFocus();
                statusLabel.setIcon(errorIcon);
                statusLabel.setText("WARNING: Family Name entered is invalid!");
            }
            else if(givenNames.isBlank() || !givenNames.matches("[a-zA-Z -]*")) {
                givenNaField.requestFocus();
                statusLabel.setIcon(errorIcon);
                statusLabel.setText("WARNING: Given Names entered is invalid!");
            }
            else {
                if(degree.equals("Medicine")) {
                    if(!prizes.isEmpty()) {
                        String[] p = prizes.split("\n");
                        if(p.length > 10) {
                            medPrizeField.requestFocus();
                            statusLabel.setIcon(errorIcon);
                            statusLabel.setText("WARNING: Medicine Prizes exceeds number limit!");
                            return;
                        }
                        prizes = "";
                        for(int i = 0; i < p.length - 1; i++) {
                            prizes += p[i] + ",";
                        }
                        prizes += p[p.length - 1];
                    }
                    try {
                        studentDB.addStudent("M,"+studentID+","+familyName+","+givenNames+","+prizes);
                        statusLabel.setIcon(successIcon);
                        statusLabel.setText("Successfully entered student to the database");
                        addStudentButton.setText("Enter New Student");
                    } catch (Exception ex) {
                        System.err.println("Failed to add Med Student.");
                        ex.printStackTrace();
                    }
                }
                else if(degree.equals("Arts")) {
                    if(major.isBlank()) {
                        artsMajorField.requestFocus();
                        statusLabel.setIcon(errorIcon);
                        statusLabel.setText("WARNING: Major entered is invalid!");
                    }
                    else if(minor.isBlank()) {
                        artsMinorField.requestFocus();
                        statusLabel.setIcon(errorIcon);
                        statusLabel.setText("WARNING: Minor entered is invalid!");
                    }
                    else {
                        try {
                            studentDB.addStudent("A,"+studentID+","+familyName+","+givenNames+","+major+","+minor);
                            statusLabel.setIcon(successIcon);
                            statusLabel.setText("Successfully entered student to the database");
                            addStudentButton.setText("Enter New Student");
                        } catch (Exception ex) {
                            System.err.println("Failed to add Arts Student.");
                            ex.printStackTrace();
                        }
                    }
                }
                else {
                    try {
                        studentDB.addStudent("S,"+studentID+","+familyName+","+givenNames);
                        statusLabel.setIcon(successIcon);
                        statusLabel.setText("Successfully entered student to the database");
                        addStudentButton.setText("Enter New Student");
                    } catch (Exception ex) {
                        System.err.println("Failed to add Science Student.");
                        ex.printStackTrace();
                    }
                }
            }
        }
        if(e.getActionCommand().equals("Find Student")) {
            String studentID = studentNoField.getText();
            if (studentID.length() != 7 || !studentID.matches("[0-9]+")) {
                studentNoField.requestFocus();
                statusLabel.setIcon(errorIcon);
                statusLabel.setText("WARNING: Student Number entered is invalid!");
            }
            else if(studentDB.findStudent(studentID) == null) {
                studentNoField.requestFocus();
                statusLabel.setIcon(errorIcon);
                statusLabel.setText("WARNING: Student Number entered does not exist!");
            }
            else {
                familyNaField.setText(studentDB.findStudent(studentID).familyName);
                givenNaField.setText(studentDB.findStudent(studentID).givenNames);
                String degree = studentDB.findStudent(studentID).getDegree();
                degreeBox.setSelectedItem(degree);
                if(degree.equals("Medicine")) {
                    String prizes = ((MedStudent) studentDB.findStudent(studentID)).getPrizes();
                    if(!prizes.isEmpty()) {
                        String[] p = prizes.split("\n");
                        prizes = "";
                        for(int i = 0; i < p.length; i++) {
                            prizes += p[i].substring(p[i].indexOf(' ') + 1) + "\n";
                        }
                    }
                    medPrizeField.setText(prizes);
                }
                else if(degree.equals("Arts")) {
                    artsMajorField.setText(((ArtsStudent) studentDB.findStudent(studentID)).getMajor());
                    artsMinorField.setText(((ArtsStudent) studentDB.findStudent(studentID)).getMinor());
                }

                statusLabel.setIcon(successIcon);
                statusLabel.setText("Successfully found student in the database");
            }
        }
        if(e.getActionCommand().equals("Add Topic Result")) {
            String studentID = studentNoField.getText();
            String code = topicCodeField.getText();
            String grade = gradeBox.getSelectedItem().toString();
            String mark = markField.getText();

            // Student ID validation and exists in database

            if (studentID.length() != 7 || !studentID.matches("[0-9]+")) {
                studentNoField.requestFocus();
                statusLabel.setIcon(errorIcon);
                statusLabel.setText("WARNING: Student Number entered is invalid!");
            }
            else if(studentDB.findStudent(studentID) == null) {
                studentNoField.requestFocus();
                statusLabel.setIcon(errorIcon);
                statusLabel.setText("WARNING: Student Number entered does not exist!");
            }
            else if(code.length() != 8 ||
                    !code.substring(0,4).matches("[a-zA-Z]+") ||
                    !code.substring(4).matches("[0-9]+")) {
                topicCodeField.requestFocus();
                statusLabel.setIcon(errorIcon);
                statusLabel.setText("WARNING: Topic Code entered is invalid!");
            }
            else {
                Student s = studentDB.findStudent(studentID);
                for(int i=0; i < s.getTopicCount(); i++) {
                    if (s.getCode(i).equals(code)) {
                        statusLabel.setIcon(errorIcon);
                        statusLabel.setText("WARNING: Topic Code entered already exists!");
                        return;
                    }
                }
                if(mark.isEmpty()) {
                    try {
                        studentDB.addResult("R," + studentID + "," + code + "," + grade);
                        statusLabel.setIcon(successIcon);
                        statusLabel.setText("Successfully added student topic results");
                    } catch (Exception ex) {
                        System.err.println("Failed to add Topic Result.");
                        ex.printStackTrace();
                    }
                }
                else if(mark.matches("[0-9]+") &&
                        Integer.parseInt(mark) >= 0 && Integer.parseInt(mark) <= 100) {
                    try {
                        studentDB.addResult("R," + studentID + "," + code + "," + grade + "," + mark);
                        statusLabel.setIcon(successIcon);
                        statusLabel.setText("Successfully added student topic results");
                    } catch (Exception ex) {
                        System.err.println("Failed to add Topic Result.");
                        ex.printStackTrace();
                    }
                }
                else {
                    markField.requestFocus();
                    statusLabel.setIcon(errorIcon);
                    statusLabel.setText("WARNING: Topic Mark entered is invalid!");
                }
            }
        }
        if(e.getActionCommand().equals("Find Topic Result")) {
            String studentID = studentNoField.getText();
            String code = topicCodeField.getText();

            if (studentID.length() != 7 || !studentID.matches("[0-9]+")) {
                studentNoField.requestFocus();
                statusLabel.setIcon(errorIcon);
                statusLabel.setText("WARNING: Student Number entered is invalid!");
            }
            else if(studentDB.findStudent(studentID) == null) {
                studentNoField.requestFocus();
                statusLabel.setIcon(errorIcon);
                statusLabel.setText("WARNING: Student Number entered does not exist!");
            }
            else if(code.length() != 8 ||
                    !code.substring(0,4).matches("[a-zA-Z]+") ||
                    !code.substring(4).matches("[0-9]+")) {
                topicCodeField.requestFocus();
                statusLabel.setIcon(errorIcon);
                statusLabel.setText("WARNING: Topic Code entered is invalid!");
            }
            else {
                Student s = studentDB.findStudent(studentID);
                for(int i=0; i < s.getTopicCount(); i++) {
                    if(s.getCode(i).equals(code)) {
                        gradeBox.setSelectedItem(s.getGrade(i));
                        markField.setText(s.getMark(i)+"");
                        statusLabel.setIcon(successIcon);
                        statusLabel.setText("Successfully found student topic results");
                        return;
                    }
                }

                clearTopicDetails();
                topicCodeField.requestFocus();
                statusLabel.setIcon(errorIcon);
                statusLabel.setText("WARNING: Topic Code entered is invalid!");
            }
        }
        if(e.getActionCommand().equals("Award Prize")) {
            String prize = prizeField.getText();
            String template = templateField.getText();
            String min = noTopicsField.getText();

            if(prize.isBlank()) {
                prizeField.requestFocus();
                statusLabel.setIcon(errorIcon);
                statusLabel.setText("WARNING: Prize Name entered is invalid!");
            }
            else if(template.length() < 4 || template.length() > 8 ||
                    !template.substring(0,4).matches("[a-zA-Z]+") ||
                    template.length() > 4 && !template.substring(4).matches("[0-9]+")) {
                templateField.requestFocus();
                statusLabel.setIcon(errorIcon);
                statusLabel.setText("WARNING: Prize Template entered is invalid!");
            }
            else if(!min.matches("[0-9]+") || Integer.parseInt(min) < 1) {
                noTopicsField.requestFocus();
                statusLabel.setIcon(errorIcon);
                statusLabel.setText("WARNING: Prize Number of Topics entered is invalid!");
            }
            else {
                try {
                    studentDB.awardPrize(prize,template,Integer.parseInt(min));
                    statusLabel.setIcon(successIcon);
                    statusLabel.setText("Successfully awarded prize to students");
                } catch (Exception ex) {
                    System.err.println("Failed to award prize.");
                    ex.printStackTrace();
                    statusLabel.setIcon(errorIcon);
                    statusLabel.setText("WARNING: Prize could not be awarded to student with maximum prizes!");
                }
            }
        }
        if(e.getActionCommand().equals("Print All Records")) {
            // divert all System.out statements to output stream.
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            System.setOut(new PrintStream(output));

            // print System.out statements as normal
            studentDB.printRecords();

            // output to a file
            String fileContent = output.toString();

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("data/record.txt"));
                writer.write(fileContent);
                writer.close();
            } catch (IOException ex) {
                System.err.println("Failed to write record file.");
                ex.printStackTrace();
            }

            // reset to System.out stream.
            System.setOut(System.out);

            // output to console
            studentDB.printRecords();
        }
        if(e.getActionCommand().equals("Clear All Records")) {
            studentDB.clearRecords();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // clear status bar
        statusLabel.setIcon(null);
        statusLabel.setText("");
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
