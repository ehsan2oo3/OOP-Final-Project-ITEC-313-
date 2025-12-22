package repository;

import model.Machine;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class MachineRepository {

    public Machine findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(Machine.class, id);
        }
    }

    public boolean update(Machine m) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(m);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        }
    }
}