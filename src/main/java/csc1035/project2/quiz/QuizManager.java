package csc1035.project2.quiz;

import csc1035.project2.HibernateUtil;
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

    /**
     * Method to add a quiz to the list of quizArrayList
     *
     * @param quiz the quiz being added to the quizArrayList
     */
    public void createQuiz(Quiz quiz) {
        if (this.quizzes.add(quiz)) {
            Session session = HibernateUtil.getSessionFactory().openSession();

            try {
                session.beginTransaction();
                session.save(quiz);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                session.getTransaction().rollback();
            } finally {
                session.close();
            }
        }
    }

    /**
     * Method to return an optional quiz object with a quizName that matches an inputted string
     *
     * @param quizName inputted string to query
     * @return quiz if quiz has a value
     */
    public Optional<Quiz> findQuizByName(String quizName) {
        for (Quiz quiz : quizzes) {
            if (Objects.equals(quiz.getQuizName().toLowerCase().trim(), quizName.toLowerCase().trim())) {
                return Optional.of(quiz);
            }
        }

        return Optional.empty();
    }

    /**
     * Method to update an existing quiz in the database with a new quiz object to override
     *
     * @param quiz the quiz object being inputted
     * @param newQuizName the new name of the quiz
     */
    public void updateQuiz(Quiz quiz, String newQuizName) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            quiz.setQuizName(newQuizName);
            session.update(quiz);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    /**
     * Method to delete a quiz from the database
     *
     * @param quiz the quiz object being deleted
     */
    public void deleteQuiz(Quiz quiz) {
        if (this.quizzes.remove(quiz)) {
            Session session = HibernateUtil.getSessionFactory().openSession();

            try {
                session.beginTransaction();
                session.delete(quiz);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                session.getTransaction().rollback();
            } finally {
                session.close();
            }
        }
    }

    public Set<Quiz> getQuizzes() {
        return this.quizzes;
    }

    public void clearQuizzes() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            for (var quiz : quizzes) {
                session.delete(quiz);
            }

            quizzes.clear();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}
