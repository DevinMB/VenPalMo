package VenPalMo.database.dao;

import VenPalMo.database.model.Currency;
import VenPalMo.database.model.Transact;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.TypedQuery;

public class CurrencyDAO {

    //TODO: Add Update Method
    //TODO: Add Delete Method

    public Currency getCurrencyIdByName(String abbreviation) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        String hql = "FROM Currency c WHERE c.name = :abr";
        TypedQuery<Currency> query = session.createQuery(hql, Currency.class);
        query.setParameter("abr", abbreviation);

        Currency result = query.getSingleResult();

        t.commit();
        factory.close();
        session.close();

        return result;
    }

    public Currency getCurrencyById(Integer currencyId) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        String hql = "FROM Currency c WHERE c.id = :id";
        TypedQuery<Currency> query = session.createQuery(hql, Currency.class);
        query.setParameter("id", currencyId);

        Currency result = query.getSingleResult();

        t.commit();
        factory.close();
        session.close();

        return result;
    }


}
