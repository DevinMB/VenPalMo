package org.devinbutts.VenPalMo.service;

import org.devinbutts.VenPalMo.dao.AccountDAO;
import org.devinbutts.VenPalMo.dao.TransactDAO;
import org.devinbutts.VenPalMo.dao.UserDAO;
import org.devinbutts.VenPalMo.model.Account;
import org.devinbutts.VenPalMo.model.User;
import org.devinbutts.VenPalMo.model.dto.TransactDTO;
import org.devinbutts.VenPalMo.model.Transact;
import org.devinbutts.VenPalMo.model.form.TransactForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class TransactService  {

    @Autowired
    TransactDAO transactDAO;

    @Autowired
    UserDAO userDAO;

    @Autowired
    AccountDAO accountDAO;

    public List<TransactDTO> findClearedTransactionsForDisplayByUserId(Integer userId){

        List<Transact> transactions = transactDAO.findAllClearedUserTransactions(userId);
        List<TransactDTO> transactDTOS = new ArrayList<>();

        for(Transact t : transactions){
             TransactDTO transactDTO = mapToDisplayTransact(t,userId);
             transactDTOS.add(transactDTO);
        }

        return transactDTOS;
    }


    public List<TransactDTO> findRequestedTransactionsForDisplayByUserId(Integer userId){

        List<Transact> transactions = transactDAO.findAllRequestedUserTransactions(userId);
        List<TransactDTO> transactDTOS = new ArrayList<>();

        for(Transact t : transactions){
            TransactDTO transactDTO = mapToDisplayTransact(t,userId);
            transactDTOS.add(transactDTO);
        }

        return transactDTOS;
    }


    public TransactDTO mapToDisplayTransact(Transact t, Integer userId){

        TransactDTO newTransact = new TransactDTO();
        newTransact.setId(t.getId());
        newTransact.setCreatedDate(t.getCreatedDate());

        if(t.getSendingUserId() == userId){
            newTransact.setUser(t.getReceivingUser());
            newTransact.setTransactionType("Sent");
            newTransact.setTransactionAmount(t.getTransactionAmount().multiply(new BigDecimal(-1)));
        }else{
            newTransact.setUser(t.getSendingUser());
            newTransact.setTransactionType("Received");
            newTransact.setTransactionAmount(t.getTransactionAmount());
        }

        newTransact.setCurrency(t.getCurrency());
        newTransact.setNote(t.getNote());

        return newTransact;
    }

    public Transact createTransactionFromForm(TransactForm transactForm){

        Transact newTransact = new Transact();

        newTransact.setCreatedDate(new Date());
        newTransact.setSendingUserId(transactForm.getSendingUserId());
        newTransact.setReceivingUserId(transactForm.getReceivingUserId());
        newTransact.setSendingUser(userDAO.findByUserId(transactForm.getSendingUserId()));
        newTransact.setReceivingUser(userDAO.findByUserId(transactForm.getReceivingUserId()));
        newTransact.setTransactionAmount(transactForm.getTransactionAmount());
        newTransact.setCurrency("USD");
        newTransact.setNote(transactForm.getNote());

        setStatus(newTransact,transactForm.getStatus());

        return newTransact;

    }

    public Transact setStatus(Transact transact,String status) {

        if(status.equals("CLEARED")){

            User sendingUser = transact.getSendingUser();
            User receivingUser = transact.getReceivingUser();

            BigDecimal transferAmt = transact.getTransactionAmount();

            Account sendingUserDefaultAccount = getDefaultAccount(sendingUser);
            Account receivingUserDefaultAccount = getDefaultAccount(receivingUser);

            BigDecimal sendingCurrentBalance = sendingUserDefaultAccount.getAvailableBalance();
            BigDecimal sendingUpdatedBalance = sendingCurrentBalance.subtract(transferAmt);

            BigDecimal receivingCurrentBalance = receivingUserDefaultAccount.getAvailableBalance();
            BigDecimal receivingUpdatedBalance = receivingCurrentBalance.add(transferAmt);

            sendingUserDefaultAccount.setAvailableBalance(sendingUpdatedBalance);
            receivingUserDefaultAccount.setAvailableBalance(receivingUpdatedBalance);

        }
        transact.setStatus(status);

        return transact;

    }

    private Account getDefaultAccount(User sendingUser) {
        List<Account> sendingUserAccounts = sendingUser.getAccounts();
        Account sendingUserDefaultAccount = null;
        for(Account acct : sendingUserAccounts){
            if(acct.getDefaultAccount()==1){
                sendingUserDefaultAccount = acct;
            }
        }
        return sendingUserDefaultAccount;
    }


}
