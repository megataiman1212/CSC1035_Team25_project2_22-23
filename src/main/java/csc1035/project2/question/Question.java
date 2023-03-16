package csc1035.project2.question;

import javax.persistence.*;

/**
 * Abstract class representing a Question object
 * Contains fields for:
 * question, topic
 *
 * @version 1
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Question {
    protected String question;
    @Enumerated(EnumType.STRING)
    protected Topic topic;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Constructor for Question class
     * @param question the prompt for the question
     * @param topic the topic of the question
     */
    public Question(String question, Topic topic) {
        this.question = question;
        this.topic = topic;
    }

    /**
     * Default empty constructor
     */
    public Question() {
    }

    /**
     * Get the question
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * toString method to return question as a string with formatting
     * @return question as a string with formatting
     */
    @Override
    public String toString() {
        return question + '\n' + "-".repeat(question.length());
    }

    public abstract boolean execute();
}

