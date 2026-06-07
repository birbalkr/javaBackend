package org.example;

import org.hibernate.Session;

public class HibernateDemoMain {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSession();

        try {
            UserClassHibernate user = new UserClassHibernate(1L, "Alice");
            session.beginTransaction();
            session.persist(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
