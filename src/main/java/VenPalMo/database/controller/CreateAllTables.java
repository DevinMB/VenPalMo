package VenPalMo.database.controller;

import VenPalMo.database.model.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class CreateAllTables {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        t.commit();
        System.out.println("successfully created tables");
        factory.close();
        session.close();
    }


}
