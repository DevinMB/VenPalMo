package org.devinbutts.VenPalMo.service;


import org.devinbutts.VenPalMo.dao.UserDAO;
import org.devinbutts.VenPalMo.model.Account;
import org.devinbutts.VenPalMo.model.User;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Autowired
    UserDAO userDAO;

    /**
     * Creates a default and secondary account, then creates a user to test the getDefaultAccount method on.
     * Should return the defaultAcct when invoked.
     * @param id
     * @param userId
     * @param currency
     * @param balance
     * @param defaultAccount
     * @throws ParseException
     */
    @ParameterizedTest
    @CsvSource(value = "1,1,USD,555.22,1")
    public void getDefaultAccount(Integer id, Integer userId, String currency, BigDecimal balance, Integer defaultAccount) throws ParseException {
        //create default account
        Account defaultAcct = new Account();
        defaultAcct.setId(id);
        defaultAcct.setUserId(userId);
        defaultAcct.setCurrency(currency);
        defaultAcct.setAvailableBalance(balance);
        defaultAcct.setDefaultAccount(defaultAccount);

        //create secondary account to add
        Account secondaryAccount = new Account();
        secondaryAccount.setId(50);
        secondaryAccount.setUserId(1);
        secondaryAccount.setCurrency("USD");
        secondaryAccount.setAvailableBalance(new BigDecimal(5000000));
        secondaryAccount.setDefaultAccount(0);

        SimpleDateFormat DateFor = new SimpleDateFormat("MM/dd/yyyy");
        //create user

        List<Account> accts = new ArrayList<>();
        accts.add(defaultAcct);
        accts.add(secondaryAccount);

        User user = new User(1,"Tod","Test","Test@gmail.com","testPass","5055053913","123 Rd","Test","TI","49534", new Date(),new Date(), "ROLE_USER",1,accts);

        Account testAccount = accountService.getDefaultAccount(user);

        Assertions.assertEquals(defaultAcct,testAccount);


    }




}
