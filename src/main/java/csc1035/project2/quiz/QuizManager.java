package csc1035.project2.quiz;

import csc1035.project2.HibernateUtil;
import csc1035.project2.IO;
import csc1035.project2.quiz.Quiz;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaQuery;
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

    /**
     * Method to add a quiz to the list of quizArrayList
     *
     * @param quiz the quiz being added to the quizArrayList
     */
    public void addQuiz(Quiz quiz) {
        this.quizzes.add(quiz);
    }

    public Optional<Quiz> findQuizByName(String quizName) {
        for (Quiz quiz : quizzes) {
            if (Objects.equals(quiz.getQuizName().toLowerCase().trim(), quizName.toLowerCase().trim())) {
                return Optional.of(quiz);
            }
        }

        return Optional.empty();
    }
}
