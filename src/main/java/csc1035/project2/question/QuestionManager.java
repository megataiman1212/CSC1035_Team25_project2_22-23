package csc1035.project2.question;

import csc1035.project2.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class QuestionManager {
    private final Set<Question> questions = new HashSet<>();

    /**
     * Constructor for the QuestionManager class that creates a hashmap of all questions in the database
     */
    public QuestionManager() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            var criteria = session.getCriteriaBuilder().createQuery(Question.class);
            criteria.from(Question.class);

            List<Question> questions = session.createQuery(criteria).list();
            this.questions.addAll(questions);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * Method to add a question instance to the database.
     *
     * @param question question being added to the database
     */
    public void createQuestion(Question question) {
        if (this.questions.add(question)) {
            Session session = HibernateUtil.getSessionFactory().openSession();

            try {
                session.beginTransaction();
                session.save(question);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                session.getTransaction().rollback();
            } finally {
                session.close();
            }
        }
    }

    /**
     * Method to update an existing question in the database with a question object
     *
     * @param question question overriding the database question
     */
    public void updateQuestion(Question question) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            session.update(question);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    /**
     * Method to delete a question from the database
     *
     * @param question the question object being deleted
     */
    public void deleteQuestion(Question question) {
        if (this.questions.remove(question)) {
            Session session = HibernateUtil.getSessionFactory().openSession();

            try {
                session.beginTransaction();
                session.delete(question);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                session.getTransaction().rollback();
            } finally {
                session.close();
            }
        }
    }

    /**
     * Get a list of all questions
     *
     * @return a set of all questions
     */
    public Set<Question> getQuestions() {
        return this.questions;
    }

    /**
     * Method to clear all questions from the database
     */
    public void clearQuestions() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            
            for (var question : questions) {
                session.delete(question);
            }

            questions.clear();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}
