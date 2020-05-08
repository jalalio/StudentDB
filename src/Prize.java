import java.util.Scanner;

/**
 * A class to represent a Prize
 */
public class Prize {
    private String name;
    private String template;
    private int topicsRequired;

    public Prize(String s) {
        Scanner scan = new Scanner(s);
        scan.useDelimiter(",");
        scan.next(); // P
        name = scan.next(); // Medicine 1 prize
        template = scan.next(); // MMED1
        topicsRequired = scan.nextInt(); // 4
    }

    public void awardPrize(StudentDatabase db) {
        db.awardPrize(name, template, topicsRequired);
    }
}
