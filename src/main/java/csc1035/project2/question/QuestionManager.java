package csc1035.project2.question;

import csc1035.project2.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.*;

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

    public Set<Question> getQuestions() {
        return this.questions;
    }

    public Optional<Question> findQuestionById(int id){
        for (Question quest:questions){
            if(Objects.equals(quest.getId() , id)){
                return Optional.of(quest);
            }
        }
        return Optional.empty();
    }

    public ArrayList<Integer> sendId(ArrayList<Question> question){
        ArrayList<Integer> ids = new ArrayList<>();
        for(Question quest: question){
                ids.add(quest.getId());
        }
        return ids;
    }

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
