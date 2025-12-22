package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public final class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private HibernateUtil() {}

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration()
                    .configure()   // loads hibernate.cfg.xml from classpath
                    .buildSessionFactory();
        } catch (Exception e) {
            throw new ExceptionInInitializerError("SessionFactory init failed: " + e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        sessionFactory.close();
    }
}