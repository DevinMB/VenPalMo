package VenPalMo.database.dao;

import VenPalMo.database.model.Currency;
import VenPalMo.database.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.TypedQuery;

public class UserDAO {

    //TODO: Add Update Method
    //TODO: Add Delete Method

    public User getUserById(Integer userId) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        String hql = "FROM User u WHERE u.id = :id";
        TypedQuery<User> query = session.createQuery(hql, User.class);
        query.setParameter("id", userId);

        User result = query.getSingleResult();

        t.commit();
        factory.close();
        session.close();

        return result;
    }



}
