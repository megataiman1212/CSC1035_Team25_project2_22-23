package csc1035.project2;

import csc1035.project2.question.Question;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Entity
public class Quiz {
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    final public List<Question> questions = new ArrayList<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String quizName;

    public Quiz(String quizName, Question... questions) {
        this.quizName = quizName;
        this.questions.addAll(Arrays.asList(questions));
    }

    public Quiz() {
        this.quizName = "Unnamed Quiz";
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String name) {
        this.quizName = name;
    }

    public void execute() {
        Collections.shuffle(questions);
        int correct = 0;

        String title = "Quiz - " + questions.size() + " questions";
        System.out.println(title);
        System.out.println("=".repeat(title.length()) + "\n");

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);

            String message = "Question " + (i + 1);
            System.out.println(message);
            System.out.println("-".repeat(message.length()));

            if (question.execute()) {
                System.out.println("Correct!");
                correct++;
            } else {
                System.out.println("Wrong");
            }

            System.out.println();
        }

        System.out.printf("You got %s/%s right!%n", correct, questions.size());
    }

    /**
     * Adds a question object to the list of questions for the quiz
     *
     * @param question the question to be added to the quiz
     */
    public void addQuestion(Question question) {
        questions.add(question);
    }
}
