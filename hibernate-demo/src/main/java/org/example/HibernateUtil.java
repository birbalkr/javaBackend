package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    public static SessionFactory sessionFactory;

    static {
        try{
            sessionFactory=new Configuration()
                    .configure()
                    .addAnnotatedClass(UserClassHibernate.class)
                    .buildSessionFactory();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
