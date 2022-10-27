import VenPalMo.database.controller.CreateDummyData;
import VenPalMo.database.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.List;

public class App {

    public static void main(String[] args){

//        CreateDummyData c1 = new CreateDummyData();
//        c1.populate();

        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        String hql = "FROM User";

        Query query = session.createQuery(hql);

        List<User> results = query.getResultList();

        for (User u : results) {
            System.out.println(u.getFirstName() + " " + u.getLastName() + " " + u.getAddress().getCity() + " " + u.getAccounts().get(0).getBalance() );
        }


    }



}
