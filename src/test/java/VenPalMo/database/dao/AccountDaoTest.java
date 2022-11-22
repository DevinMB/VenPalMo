package VenPalMo.database.dao;

import org.devinbutts.VenPalMo.model.Account;
import org.devinbutts.VenPalMo.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

public class AccountDaoTest {
//# id, balance, default_acct, currency_id, user_id


    //TODO: Add Create Acct DAO Test
    //TODO: Add Update Acct DAO Test
    //TODO: Add Delete Acct DAO Test
    
//    @ParameterizedTest
//    @CsvSource({ "1,253.21,1,1,1","2,555253.21,1,1,2","3,233555.21,1,1,3"})
//    protected void getAccountTest(Integer id, String balance, Integer defaultAccount, Integer currencyId, Integer userId) {
//            Account testAccount = new Account();
//            testAccount.setId(id);
//            testAccount.setAvailableBalance(new BigDecimal(balance));
//            boolean defaultAcct = false;
//            if(defaultAccount==1){defaultAcct = true;}
//            testAccount.setDefaultAccount(defaultAcct);
//
//            CurrencyDAO currencyDAO = new CurrencyDAO();
//            Currency c = currencyDAO.getCurrencyById(currencyId);
//            testAccount.setCurrency(c);
//
//            UserDAO UserDAO = new UserDAO();
//            User u = UserDAO.getUserById(userId);
//
//            //Get Account By Id
//            AccountDAO accountDAO = new AccountDAO();
//            Account a = accountDAO.getAccountById(id);
//
//            //AssertEquals
//            Assertions.assertEquals(testAccount,a);
//
//    }

}
