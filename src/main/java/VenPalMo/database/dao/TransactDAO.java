package VenPalMo.database.dao;

import VenPalMo.database.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class TransactDAO {

    //TODO: Add Delete Method

    public void insertTransaction(Transact transaction) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        session.save(transaction);

        t.commit();
        factory.close();
        session.close();
    }

    public void updateTransaction(Transact transaction) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        session.merge(transaction);

        t.commit();
        factory.close();
        session.close();
    }

    public Transact getTransactionById(Integer transactId) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        String hql = "FROM Transact t WHERE t.id = :transactId";
        TypedQuery<Transact> query = session.createQuery(hql, Transact.class);
        query.setParameter("transactId", transactId);

        Transact result = query.getSingleResult();

        t.commit();
        factory.close();
        session.close();
        return result;
    }

    public boolean changeTransactStatusTo(Transact transaction, String transactionStatusName) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        String hql = "UPDATE Transact t SET t.status.id = :statusId WHERE t.id = :transactId";
        Query query = session.createQuery(hql);

        TransactionStatusDAO tsDao = new TransactionStatusDAO();

        TransactionStatus requestedStatus = tsDao.getFromStatusName(transactionStatusName);

        Integer statusId = requestedStatus.getId();

        query.setParameter("statusId", statusId);
        query.setParameter("transactId", transaction.getId());

        int count = query.executeUpdate(); //return type wrong

        boolean success = false;
        if (count == 1) {
            success = true;
        }

        t.commit();
        factory.close();
        session.close();

        return success;
    }
}
