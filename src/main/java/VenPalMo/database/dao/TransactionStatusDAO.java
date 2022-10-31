package VenPalMo.database.dao;

import VenPalMo.database.model.TransactionStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.TypedQuery;

public class TransactionStatusDAO {

    public TransactionStatus getFromStatusName(String transactionStatusName ) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        String hql = "FROM TransactionStatus ts WHERE ts.name = :status";

        TypedQuery<TransactionStatus> query = session.createQuery(hql, TransactionStatus.class);
        query.setParameter("status", transactionStatusName);

        TransactionStatus result = query.getSingleResult();

        t.commit();
        factory.close();
        session.close();

        return result;
    }


}
