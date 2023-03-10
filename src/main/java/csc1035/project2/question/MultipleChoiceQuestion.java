package csc1035.project2.question;

import csc1035.project2.IO;

import javax.persistence.*;
import java.util.*;

@Entity
@PrimaryKeyJoinColumn
public class MultipleChoiceQuestion extends Question {
    @ElementCollection
    protected Collection<String> wrongAnswers;
    protected String correctAnswer;

    public MultipleChoiceQuestion(String question, Topic topic, String correctAnswer, String... wrongAnswers) {
        super(question, topic);
        this.correctAnswer = correctAnswer;
        this.wrongAnswers = List.of(wrongAnswers);
    }

    public MultipleChoiceQuestion() {
    }

    public boolean execute() {
        // Set up answers
        ArrayList<String> allAnswers = new ArrayList<>(wrongAnswers);
        allAnswers.add(correctAnswer);
        Collections.shuffle(allAnswers);

        // Print question to console
        System.out.println(question);

        // Print answers to console
        for (int i = 0; i < allAnswers.size(); i++) {
            System.out.printf("\t%s - %s%n", i + 1, allAnswers.get(i));
        }

        // Get user input
        System.out.print("Enter your choice... ");
        int answer;
        while (true) {
            String line = IO.scanner.nextLine().trim();
            try {
                answer = Integer.parseInt(line);
                if (answer >= 1 && answer <= allAnswers.size()) break;
            } catch (Exception ignored) {
            }

            System.out.print("Invalid choice, try again... ");
        }

        // Test if the correct answer matches the selected answer
        return allAnswers.get(answer - 1).equals(correctAnswer);
    }
}
