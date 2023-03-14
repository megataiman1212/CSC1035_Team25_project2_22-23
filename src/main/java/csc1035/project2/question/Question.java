package csc1035.project2.question;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Question {
    protected String question;
    @Enumerated(EnumType.STRING)
    protected Topic topic;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Question(String question, Topic topic) {
        this.question = question;
        this.topic = topic;
    }

    public Question() {
    }

    public String getQuestion() {
        return question;
    }

    @Override
    public String toString() {
        return question + '\n' + "-".repeat(question.length());
    }

    public abstract boolean execute();
}

