package csc1035.project2.question;

import java.util.List;

public class MultipleChoiceQuestion extends Question {
    final List<String> answers;

    public MultipleChoiceQuestion(String question, Topic topic, List<String> answers) {
        super(question, topic);
        this.answers = answers;
    }

    @Override
    void answer() {
        // @TODO
    }
}
