package org.devinbutts.VenPalMo.service;

import org.devinbutts.VenPalMo.model.Account;
import org.devinbutts.VenPalMo.model.Transact;
import org.devinbutts.VenPalMo.model.User;
import org.devinbutts.VenPalMo.model.dto.TransactDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class TransactServiceTest {

    @Autowired
    TransactService transactService;



    @Test
    protected void mapToDisplayTransact(){

        User userOne = createTestUser();
        User userTwo = createTestUser();
        userTwo.setId(555);

        //create Transaction

        Transact newTransact = new Transact();

        newTransact.setId(5000);
        newTransact.setCurrency("USD");
        newTransact.setStatus("CLEARED");
        newTransact.setTransactionAmount(new BigDecimal(5000));
        newTransact.setNote("TestTestTest");
        newTransact.setReceivingUser(userOne);
        newTransact.setSendingUser(userTwo);
        newTransact.setCreatedDate(new Date());

        //create expected TransactDTO
        TransactDTO expectedTransactDTO = new TransactDTO();
        expectedTransactDTO.setTransactionType("Received");
        expectedTransactDTO.setUser(userTwo);
        expectedTransactDTO.setNote("TestTestTest");
        expectedTransactDTO.setTransactionAmount(new BigDecimal(5000));
        expectedTransactDTO.setId(5000);
        expectedTransactDTO.setCreatedDate(newTransact.getCreatedDate());
        expectedTransactDTO.setCurrency("USD");



        //invoke method

        TransactDTO testTransactDTO = transactService.mapToDisplayTransact(newTransact,50);

        //assert

        Assertions.assertEquals(expectedTransactDTO,testTransactDTO);

    }

    public User createTestUser(){
        //create user
        Account account = new Account();
        account.setId(50);
        account.setUserId(1);
        account.setCurrency("USD");
        account.setAvailableBalance(new BigDecimal(5000000));
        account.setDefaultAccount(1);
        SimpleDateFormat DateFor = new SimpleDateFormat("MM/dd/yyyy");
        List<Account> accts = new ArrayList<>();
        accts.add(account);
        User user = new User(1,"Tod","Test","Test@gmail.com","testPass","5055053913","123 Rd","Test","TI","49534", new Date(),new Date(), "ROLE_USER",1,accts);
        return user;
    }




}
