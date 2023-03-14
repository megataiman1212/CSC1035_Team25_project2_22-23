package csc1035.project2.question;

import csc1035.project2.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuestionManager {
    private final Set<Question> questions = new HashSet<>();

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

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public void removeQuestion(Question question) {
        this.questions.remove(question);
    }

    public Set<Question> getQuestions() {
        return this.questions;
    }
}
