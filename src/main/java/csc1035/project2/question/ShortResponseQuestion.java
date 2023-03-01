package csc1035.project2.question;

import csc1035.project2.IO;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ShortResponseQuestion extends Question {
    final Pattern pattern;

    public ShortResponseQuestion(String question, Topic topic, Pattern pattern) {
        super(question, topic);
        this.pattern = pattern;
    }

    @Override
    public boolean execute() {
        showPrompt();
        String input = getInput();

        return pattern.matcher(input).matches();
    }

    public void showPrompt() {
        System.out.println(question);
    }

    public String getInput() {
        System.out.print("Enter your answer... ");

        return IO.scanner.nextLine().trim();
    }
}
