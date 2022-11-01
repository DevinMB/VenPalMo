package VenPalMo.database.util;

import VenPalMo.database.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.math.BigDecimal;
import java.time.LocalDate;

public class DatabaseSetup {
    public static void main(String[] args) {

        populateData();


    }


    public static void populateData() {
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
        u2.addAccount(a2);
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
        Account a3 = new Account(u3,usd,new BigDecimal("6533555.21"),true);
        u3.addAccount(a3);
        session.save(u3);

        //Tori
        User u4 = new User("Tori",
                "Butts",
                "hallwoo@gmail.com",
                "test",
                "598-505-6540",
                new Address("1185 Code St.","Grand Rapids","MI","49534"),
                LocalDate.of(2010,11,2),
                LocalDate.of(1994,4,2),
                "USER",
                true);
        Account a4 = new Account(u4,usd,new BigDecimal("2335564055.21"),true);
        u4.addAccount(a4);
        session.save(u4);

        //Justin
        User u5 = new User("Justin",
                "Frank",
                "justin@gmail.com",
                "test",
                "598-555-6540",
                new Address("9805 WOw St.","Grand Rapids","MI","49554"),
                LocalDate.of(2010,11,2),
                LocalDate.of(1990,3,22),
                "USER",
                true);
        Account a5 = new Account(u5,usd,new BigDecimal("133555.21"),true);
        u5.addAccount(a5);
        session.save(u5);

        //Cookie
        User u6 = new User("Cookie",
                "Knopf",
                "cook@gmail.com",
                "test",
                "598-555-8900",
                new Address("23542 Sheesh St.","Kalamazoo","TX","50885"),
                LocalDate.of(2010,11,2),
                LocalDate.of(1973,3,22),
                "USER",
                true);
        Account a6 = new Account(u6,usd,new BigDecimal("933555.21"),true);
        u6.addAccount(a6);
        session.save(u6);

        //Liz
        User u7 = new User("Liz",
                "Knopf",
                "liz@gmail.com",
                "test",
                "598-555-9900",
                new Address("23542 Sheesh St.","Kalamazoo","TX","50885"),
                LocalDate.of(2010,11,2),
                LocalDate.of(1983,3,22),
                "USER",
                true);
        Account a7 = new Account(u7,usd,new BigDecimal("433555.21"),true);
        u7.addAccount(a7);
        session.save(u7);

        //CREATE SOME TRANSACTION
        Message mt1 = new Message("Wow here you go!",u1,u2); session.save(mt1);
        Transact t1 = new Transact(
                u2,
                u1,
                new BigDecimal("5468.25"),
                usd,
                mt1,
                pending);
        session.save(t1);

        Message mt2 = new Message("Yo!",u2,u3); session.save(mt2);
        Transact t2 = new Transact(
                u3,
                u2,
                new BigDecimal("548.25"),
                usd,
                mt2,
                pending);
        session.save(t2);

        Message mt3 = new Message("Yo!",u7,u6); session.save(mt3);
        Transact t3 = new Transact(
                u6,
                u7,
                new BigDecimal("3348.25"),
                usd,
                mt3,
                pending);
        session.save(t3);


        //CREATE SOME MESSAGES
        Message m1 = new Message("Hi do you have money??",u2,u4); session.save(m1);
        Message m2 = new Message("Yeah how much you want?",u4,u2); session.save(m2);
        Message m3 = new Message("Hows your day, want to get coffee",u5,u1); session.save(m3);
        Message m4 = new Message("Good! Yeah lets do it",u1,u5); session.save(m4);
        Message m5 = new Message("Pizza was 50.30",u3,u7); session.save(m5);



        t.commit();
        System.out.println("successfully created dummy data");
        factory.close();
        session.close();
    }


}
