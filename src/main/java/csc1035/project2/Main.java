package csc1035.project2;

import csc1035.project2.question.MultipleChoiceQuestion;
import csc1035.project2.question.ShortResponseQuestion;
import csc1035.project2.question.Topic;

import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        // @TODO Delete this! - It's just a demo of how to create the questions and execute them

        MultipleChoiceQuestion mcq = new MultipleChoiceQuestion(
                "Select 'alpha' for correct, anything else for incorrect",
                Topic.PROGRAMMING,
                "alpha",
                "bravo", "charlie", "delta");
        mcq.execute();

        System.out.println("-".repeat(25));

        ShortResponseQuestion srq = new ShortResponseQuestion(
                "Type 'alpha' (case insensitive) for correct, anything else for incorrect",
                Topic.MATHS,
                Pattern.compile("^alpha$", Pattern.CASE_INSENSITIVE)
        );
        srq.execute();
    }
}
