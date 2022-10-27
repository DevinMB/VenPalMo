package VenPalMo.database.controller;

import VenPalMo.database.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class CreateDummyData {

    public void populate() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        //SET TRANSACTION STATUS TYPES
        TransactionStatus approved = new TransactionStatus("APPROVED");
        session.save(approved);

        TransactionStatus pending = new TransactionStatus("PENDING");
        session.save(pending);

        TransactionStatus rejected = new TransactionStatus("REJECTED");
        session.save(rejected);

        //SET A FEW CURRENCIES
        Currency usd = new Currency("USD");
        session.save(usd);

        //CREATE SOME USERS
        //Devin
        User u1 = new User("Devin",
                            "Butts",
                            "devinmbutts@gmail.com",
                            "test",
                            "248-505-3913",
                            new Address("1185 Code St.","Grand Rapids","MI","49534"),
                            LocalDate.of(2010,11,2),
                            LocalDate.of(1993,7,2),
                            "ADMIN",
                            true);
        Account a1 = new Account(u1,usd,new BigDecimal("253.21"),true);
        u1.addAccount(a1);
        session.save(u1);

        //Nick
        User u2 = new User("Nick",
                "Ruffus",
                "nRuf@gmail.com",
                "test",
                "248-505-6540",
                new Address("1185 Code St.","Grand Rapids","MI","49534"),
                LocalDate.of(2010,11,2),
                LocalDate.of(1994,3,2),
                "USER",
                true);
        Account a2 = new Account(u2,usd,new BigDecimal("555253.21"),true);
        u1.addAccount(a2);
        session.save(u2);

        //Chris
        User u3 = new User("Chris",
                "Channells",
                "cchannells@gmail.com",
                "test",
                "558-505-6540",
                new Address("1185 Code St.","Grand Rapids","MI","49534"),
                LocalDate.of(2010,11,2),
                LocalDate.of(1992,4,2),
                "USER",
                true);
        Account a3 = new Account(u3,usd,new BigDecimal("233555.21"),true);
        u1.addAccount(a3);
        session.save(u3);
























        t.commit();
        System.out.println("successfully created dummy data");
        factory.close();
        session.close();
    }



}
