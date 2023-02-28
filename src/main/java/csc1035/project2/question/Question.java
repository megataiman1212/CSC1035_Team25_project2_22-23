package csc1035.project2.question;

public abstract class Question {
    final protected String question;
    final protected Topic topic;

    public Question(String question, Topic topic) {
        this.question = question;
        this.topic = topic;
    }

    public String getQuestion() {
        return question;
    }

    public abstract void execute();
}

