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
        List<Question> questions = new ArrayList<>(this.questions);
        var userAnswers = new ArrayList<Boolean>();
        Collections.shuffle(questions);

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

                // Add a boolean value to list signifying correct answer
                userAnswers.add(true);

            } else {
                System.out.println("Wrong");

                // Add a boolean value to list signifying incorrect answer
                userAnswers.add(false);
            }

            System.out.println();
        }

        System.out.println("Would you like to see a report on your results");
        System.out.println("----------------");
        System.out.println("Yes, No");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        String reportChoice = scanner.nextLine().trim().toLowerCase();

        while (reportChoice.isEmpty() || (!Objects.equals(reportChoice, "yes") && !Objects.equals(reportChoice, "no"))) {
            System.out.println("Choice not recognised");
            System.out.println("Yes, No");
            System.out.println("Try again : ");
            reportChoice = scanner.nextLine().trim().toLowerCase();
        }
        if (reportChoice.equals("yes")){ System.out.println(generateReport(questions, userAnswers));}
    }

    /**
     * Adds a question object to the list of questions for the quiz
     *
     * @param question the question to be added to the quiz
     */
    public void addQuestion(Question question) {
        questions.add(question);
    }

    public String generateReport(List<Question> questions, List<Boolean> answers) {
        int correctCount = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("Quiz Results\n\n");
        for (int i = 0; i < questions.size(); i++) {
            sb.append("Question ").append(i + 1).append(": ").append(questions.get(i).getQuestion()).append("\n");
            sb.append("Your answer: ").append(answers.get(i) ? "Correct" : "Incorrect").append("\n");
            sb.append("Correct answer: ").append(questions.get(i).getAnswer()).append("\n\n");
            if (answers.get(i)) {
                correctCount++;
            }
        }
        double percentageScore = (double) correctCount / questions.size() * 100;
        sb.append("Final score: ").append(String.format("%.2f", percentageScore)).append("%\n");
        return sb.toString();
    }
}
