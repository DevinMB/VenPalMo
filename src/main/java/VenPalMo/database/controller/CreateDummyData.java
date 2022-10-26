package VenPalMo.database.controller;

import VenPalMo.database.model.TransactionStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class CreateDummyData {

    public void populate() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        //SET TRANSACTION STATUS TYPES
        TransactionStatus approved = new TransactionStatus();
        approved.setName("APPROVED");
        session.save(approved);

        TransactionStatus pending = new TransactionStatus();
        pending.setName("PENDING");
        session.save(pending);

        TransactionStatus rejected = new TransactionStatus();
        rejected.setName("REJECTED");
        session.save(rejected);













        t.commit();
        System.out.println("successfully created dummy data");
        factory.close();
        session.close();
    }



}
