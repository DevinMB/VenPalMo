import VenPalMo.database.dao.TransactDAO;
import VenPalMo.database.model.Transact;

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

        boolean success = td.changeTransactStatusTo(requestedTransaction,"REJECTED");

        Transact alteredTransaction = td.getTransactionById(1);

        System.out.println(alteredTransaction.getId() + "With a Status of : " + alteredTransaction.getStatus().getName());


        //Todo:address dao
        //Todo:message dao
        //TODO: EACH CLASS SHOULD HAVE COMMENTS ON WHAT IT DOES

    }



}
