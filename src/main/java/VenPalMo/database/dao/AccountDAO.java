package VenPalMo.database.dao;

import VenPalMo.database.model.Account;
import VenPalMo.database.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.TypedQuery;

public class AccountDAO {

    //TODO: Add Update Method
    //TODO: Add Delete Method

    public Account getAccountById(Integer accountId) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        String hql = "FROM Account a WHERE a.id = :id";
        TypedQuery<Account> query = session.createQuery(hql, Account.class);
        query.setParameter("id", accountId);

        Account result = query.getSingleResult();

        t.commit();
        factory.close();
        session.close();

        return result;
    }



}
