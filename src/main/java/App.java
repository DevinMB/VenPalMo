import VenPalMo.database.dao.TransactDAO;
import VenPalMo.database.model.Transact;
import VenPalMo.database.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.List;

public class App {

    public static void main(String[] args){
//
//        SessionFactory factory = new Configuration().configure().buildSessionFactory();
//        Session session = factory.openSession();
//
//        String hql = "FROM User";
//
//        Query query = session.createQuery(hql);
//
//        List<User> results = query.getResultList();
//
//        for (User u : results) {
//            System.out.println(u.getFirstName() + " " + u.getLastName() + " " + u.getAddress().getCity() + " " + u.getAccounts().get(0).getBalance() );
//        }
//

        TransactDAO td = new TransactDAO();

        Transact requestedTransaction = td.getTransactionById(1);

        System.out.println("ID : " + requestedTransaction.getId() + " With a Status of : " + requestedTransaction.getStatus().getName());

        System.out.println("now changing to approved");

        td.changeTransactStatusTo(requestedTransaction,"APPROVED");

        Transact alteredTransaction = td.getTransactionById(1);

        System.out.println(alteredTransaction.getId() + "With a Status of : " + alteredTransaction.getStatus().getName());




    }



}
