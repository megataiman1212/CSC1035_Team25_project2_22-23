package csc1035.project2;

import csc1035.project2.question.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Quiz {
    final private ArrayList<Question> questions;

    public Quiz(Question... questions) {
        this.questions = new ArrayList<>(Arrays.asList(questions));
    }

    public void execute() {
        Collections.shuffle(questions);
        int correct = 0;

        String title = "Quiz - " + questions.size() + " questions";
        System.out.println(title);
        System.out.println("=".repeat(title.length()) + "\n");

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);

            String message = "Question " + (i+1);
            System.out.println(message);
            System.out.println("-".repeat(message.length()));

            if(question.execute()) {
                System.out.println("Correct!");
                correct++;
            } else {
                System.out.println("Wrong");
            }

            System.out.println();
        }

        System.out.printf("You got %s/%s right!%n", correct, questions.size());
    }
}
