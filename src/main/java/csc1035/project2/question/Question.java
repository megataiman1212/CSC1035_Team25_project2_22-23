package csc1035.project2.question;

public abstract class Question {

    // @todo add hibernate notation (@id or @column etc..)
    final protected String question;
    final protected Topic topic;

    // @todo add a no arg/default constructor

    public Question(String question, Topic topic) {
        this.question = question;
        this.topic = topic;
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

