package csc1035.project2.question;

import csc1035.project2.IO;

import javax.persistence.*;
import java.util.regex.Pattern;

/**
 * Subclass of Question to represent a ShortResponseQuestion
 * Contains fields for:
 * question, topic, pattern
 * Contains methods for:
 * - Method to execute the main prompt string for the question
 *
 * @version 1
 */
@Entity
@PrimaryKeyJoinColumn
public class ShortResponseQuestion extends Question {
    // @TODO Store the pattern in hibernate as a regex string rather than a blob
    protected Pattern pattern;

    /**
     * Constructor for creating ShortResponseQuestion Instance
     *
     * @param question the prompt for the question
     * @param topic the topic for the question
     * @param pattern the answer/pattern of the question
     */
    public ShortResponseQuestion(String question, Topic topic, Pattern pattern) {
        super(question, topic);
        this.pattern = pattern;
    }

    /**
     * Default empty constructor
     */
    public ShortResponseQuestion() {
    }

    /**
     * Set the answer/pattern
     * @param pattern the answer/pattern
     */
    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    /**
     * Method to return a string value for pattern
     *
     * @return string value for pattern
     */
    @Column
    public String stringPattern() {
        return pattern.toString();
    }

    /**
     * Method to execute the main prompt string for the question, implemented from question.java
     *
     * @return @todo what does this return
     */
    @Override
    public boolean execute() {
        // Print the question to console
        System.out.println(question);

        // Get user input
        System.out.print("Enter your answer... ");
        String input = IO.scanner.nextLine().trim();

        // Test if input matches the regex
        return pattern.matcher(input).matches();
    }
}
