package csc1035.project2.question;

import csc1035.project2.IO;

import javax.persistence.*;
import java.util.*;

/**
 * Subclass of Question to represent a MultipleChoiceQuestion
 * Contains fields for:
 * question, topic, correctAnswer, wrongAnswers
 * Contains methods for:
 * - Method to execute the main prompt string for the question
 *
 * @version 1
 */
@Entity
@PrimaryKeyJoinColumn
public class MultipleChoiceQuestion extends Question {
    @ElementCollection
    protected Collection<String> wrongAnswers;
    protected String correctAnswer;

    /**
     * Constructor for creating a MultipleChoiceQuestion instance
     *
     * @param question the prompt for the question
     * @param topic the topic for the question
     * @param correctAnswer the correct answer for the question
     * @param wrongAnswers a list of wrong answers for the question
     */
    public MultipleChoiceQuestion(String question, Topic topic, String correctAnswer, String... wrongAnswers) {
        super(question, topic);
        this.correctAnswer = correctAnswer;
        this.wrongAnswers = List.of(wrongAnswers);
    }

    /**
     * Default empty constructor
     */
    public MultipleChoiceQuestion() {
    }

    public Collection<String> getWrongAnswers() {
        return wrongAnswers;
    }

    @Override
    public String getAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setWrongAnswers(Collection<String> wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }

    /**
     * Method to execute the main prompt string for the question, implemented from question.java
     * @return @todo what does this return
     */
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
