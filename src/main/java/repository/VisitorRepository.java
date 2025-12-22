package repository;

import model.Visitor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class VisitorRepository {

    public Visitor findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(Visitor.class, id);
        }
    }

    public boolean update(Visitor v) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(v); // update detached entity
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        }
    }
}