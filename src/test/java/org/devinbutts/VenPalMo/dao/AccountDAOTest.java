package org.devinbutts.VenPalMo.dao;


import org.devinbutts.VenPalMo.model.Account;
import org.devinbutts.VenPalMo.model.User;
import org.hibernate.annotations.Source;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;


@SpringBootTest
@ActiveProfiles("test")
public class AccountDAOTest {

    @Autowired
    AccountDAO accountDAO;

    @Autowired
    UserDAO userDAO;


    @Sql({"/database/test_data.sql"})
    @ParameterizedTest()
    @CsvFileSource(resources = {"/test/account.csv"},delimiter = ',',numLinesToSkip = 1)
    protected void getAllAccounts(Integer id, Integer userId, String currency, BigDecimal balance, Integer defaultAcct){

            Account expectedAccount = new Account();

            expectedAccount.setId(id);
            expectedAccount.setUserId(userId);
            expectedAccount.setCurrency(currency);
            expectedAccount.setAvailableBalance(balance);
            expectedAccount.setDefaultAccount(defaultAcct);

            List<Account> testAccounts = accountDAO.findAll();

            Account testAccount = new Account();

            for(Account a: testAccounts){
                if(a.getId()==expectedAccount.getId()){
                    testAccount = a;
                }
            }

            Assertions.assertEquals(expectedAccount, testAccount);

    }

    @Sql({"/database/test_data.sql"})
    @ParameterizedTest
    @CsvSource(value = "1,1,USD,555.22,1")
    protected void findDefaultAccount(Integer id,Integer userId, String currency, BigDecimal balance, Integer defaultAcct){
        Account expectedAccount = new Account();
        expectedAccount.setId(id);
        expectedAccount.setUserId(userId);
        expectedAccount.setCurrency(currency);
        expectedAccount.setAvailableBalance(balance);
        expectedAccount.setDefaultAccount(defaultAcct);

        Account testAccount = accountDAO.findDefaultAccountByUserId(id);

        Assertions.assertEquals(expectedAccount,testAccount);



    }

    @Sql({"/database/test_data.sql"})
    @ParameterizedTest
    @CsvSource(value = "1,USD,5550.22,0")
    protected void saveNewAccount(Integer userId, String currency, BigDecimal balance, Integer defaultAcct){
        Account expectedAccount = new Account();

        User user = userDAO.findByUserId(1);

        expectedAccount.setUser(userDAO.findByEmail("devinmbutts@gmail.com"));
        expectedAccount.setCurrency(currency);
        expectedAccount.setAvailableBalance(balance);
        expectedAccount.setDefaultAccount(defaultAcct);

        accountDAO.save(expectedAccount);

        List<Account> testAccounts = accountDAO.findByUserId(userId);
        Account testAccount = new Account();
        for(Account a : testAccounts){
            if(a.getAvailableBalance()==balance){
                testAccount=a;
            }
        }

        Assertions.assertEquals(expectedAccount,testAccount);



    }


}
