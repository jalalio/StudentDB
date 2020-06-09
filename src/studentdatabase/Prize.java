package studentdatabase;

import java.util.Scanner;

/**
 * A class to represent a Prize
 */
public class Prize {
    private String name;
    private String template;
    private int topicsRequired;

    public Prize(String s) throws Exception {
        Scanner scan = new Scanner(s);
        scan.useDelimiter(",");
        scan.next(); // P
        name = scan.next(); // Medicine 1 prize
        if(name.isEmpty()) {
            throw new Exception("Prize name is not Given");
        }
        template = scan.next().toUpperCase(); // MMED1
        if(template.isEmpty()) {
            throw new Exception("Topic requirement for Prize name is not Given");
        }
        topicsRequired = scan.nextInt(); // 4
        if(topicsRequired < 1) {
            throw new Exception("Min Topic requirement for Prize error");
        }
    }

    public void awardPrize(StudentDatabase db) throws Exception {
        db.awardPrize(name, template, topicsRequired);
    }
}
