package repository;

import model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class EmployeeRepository {

    /* -------- READ -------- */
    public Employee findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(Employee.class, id); // Hibernate 7 correct API
        }
    }

    public List<Employee> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "from Employee", Employee.class
            ).getResultList();
        }
    }

    /* -------- WRITE -------- */

    public boolean save(Employee employee) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(employee);   // for NEW employees
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        }
    }

    public boolean update(Employee employee) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(employee);     // for DETACHED employees
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        }
    }

    public boolean deleteById(Long id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            Employee e = session.find(Employee.class, id);
            if (e == null) {
                tx.rollback();
                return false;
            }

            session.remove(e);
            tx.commit();
            return true;

        } catch (Exception ex) {
            if (tx != null) tx.rollback();
            return false;
        }
    }
}
