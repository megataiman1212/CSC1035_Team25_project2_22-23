package csc1035.project2;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.*;

/**
 *
 */
public class QuizManager {
    private final Set<Quiz> quizzes = new HashSet<>();

    /**
     * Constructor for the QuizManager class that creates a list of quizzes
     */
    public QuizManager() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            var criteria = session.getCriteriaBuilder().createQuery(Quiz.class);
            criteria.from(Quiz.class);

            List<Quiz> quizzes = session.createQuery(criteria).list();
            this.quizzes.addAll(quizzes);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Quiz selectQuiz() {
        Scanner scanner = new Scanner(System.in);

        // Allow user input for the quizName
        System.out.println("Enter the name of the quiz : ");
        String quizName = scanner.nextLine().toLowerCase();

        // Assigns the quiz variable with the quiz found by the searchQuizByName
        return searchQuizByName(quizName);
    }

    /**
     * Method to add a quiz to the list of quizArrayList
     *
     * @param quiz the quiz being added to the quizArrayList
     */
    public void addQuiz(Quiz quiz) {
        this.quizzes.add(quiz);
    }

    public Quiz searchQuizByName(String quizName) {
        for (Quiz quiz : quizzes) {
            String currentQuizName = quiz.getQuizName();

            if (Objects.equals(currentQuizName, quizName)) {
                System.out.println("Quiz Found");
                return quiz;
            }
        }
        System.out.println("No quiz found with the name \"" + quizName + "\"");
        return null;
    }
}
