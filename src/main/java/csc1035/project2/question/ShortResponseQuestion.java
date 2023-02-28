package csc1035.project2.question;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ShortResponseQuestion extends Question {
    final Pattern pattern;

    public ShortResponseQuestion(String question, Topic topic, Pattern pattern) {
        super(question, topic);
        this.pattern = pattern;
    }

    @Override
    public void execute() {
        System.out.println(this.question);

        // @TODO Refactor this into an `IO` class

        Scanner s = new Scanner(System.in);

        String answer = s.nextLine().trim();
        if(pattern.matcher(answer).matches()) {
            System.out.println("Correct :)");
        } else {
            System.out.println("Wrong :(");
        }

    }
}
