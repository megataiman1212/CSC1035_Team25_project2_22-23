package csc1035.project2.quiz;

import csc1035.project2.question.Question;

import javax.persistence.*;
import java.util.*;

/**
 * Class to represent a quiz and all questions that belong to it
 * Contains fields for:
 * id, quizName, questions
 * Contains methods for:
 * - Method to execute the list of questions, by displaying the question prompt
 *
 * @version 1
 */
@Entity
public class Quiz {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn
    final public Set<Question> questions = new HashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String quizName;

    /**
     * Constructor for Quiz that takes a list of questions and a quizName
     * @param quizName the name of the quiz
     * @param questions the questions to be added to the quiz
     */
    public Quiz(String quizName, Question... questions) {
        this.quizName = quizName;
        this.questions.addAll(Arrays.asList(questions));
    }

    /**
     * Default constructor allowing a default quizName value
     */
    public Quiz() {
        this.quizName = "Unnamed Quiz";
    }

    /**
     * Get the quizName
     * @return the quizName
     */
    public String getQuizName() {
        return quizName;
    }

    /**
     * Set the quizName
     * @param name name of the quiz
     */
    public void setQuizName(String name) {
        this.quizName = name;
    }

    /**
     * Method to execute the list of questions, by displaying the question prompt
     */
    public void execute() {
        var questions = new ArrayList<>(this.questions);
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

        // @ todo allow the user to see questions they got wrong/see the answers to mark it themselves
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
