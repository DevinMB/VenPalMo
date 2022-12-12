package org.devinbutts.VenPalMo.service;

import lombok.extern.slf4j.Slf4j;
import org.devinbutts.VenPalMo.dao.AccountDAO;
import org.devinbutts.VenPalMo.dao.TransactDAO;
import org.devinbutts.VenPalMo.dao.UserDAO;
import org.devinbutts.VenPalMo.model.Account;
import org.devinbutts.VenPalMo.model.User;
import org.devinbutts.VenPalMo.model.dto.TransactDTO;
import org.devinbutts.VenPalMo.model.Transact;
import org.devinbutts.VenPalMo.model.form.TransactForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Transact Service primarily used for displaying transactions to user, changing status, and converting between Transact DTO.
 */
@Slf4j
@Service
public class TransactService  {

    @Autowired
    TransactDAO transactDAO;

    @Autowired
    UserDAO userDAO;

    @Autowired
    AccountDAO accountDAO;

    @Autowired
    AccountService accountService;

    public List<TransactDTO> findClearedTransactionsForDisplayByUserId(Integer userId){

        List<Transact> transactions = null;
        List<TransactDTO> transactDTOS = new ArrayList<>();

        try{
            transactions = transactDAO.findAllClearedUserTransactions(userId);

            for(Transact t : transactions){
                TransactDTO transactDTO = mapToDisplayTransact(t,userId);
                transactDTOS.add(transactDTO);
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }

        return transactDTOS;
    }


    public List<TransactDTO> findRequestedTransactionsForDisplayByUserId(Integer userId){

        List<Transact> transactions = null;
        List<TransactDTO> transactDTOS = new ArrayList<>();

        try{
            transactions = transactDAO.findAllRequestedUserTransactions(userId);
            for(Transact t : transactions){
                TransactDTO transactDTO = mapToDisplayTransact(t,userId);
                transactDTOS.add(transactDTO);
            }
            for(TransactDTO t: transactDTOS){
                t.setTransactionAmount(t.getTransactionAmount().abs());
            }
        }catch (Exception e){
            log.error(e.getMessage());
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

            Account sendingUserDefaultAccount = accountService.getDefaultAccount(sendingUser);
            Account receivingUserDefaultAccount = accountService.getDefaultAccount(receivingUser);

            BigDecimal sendingCurrentBalance = sendingUserDefaultAccount.getAvailableBalance();
            BigDecimal sendingUpdatedBalance = sendingCurrentBalance.subtract(transferAmt);

            BigDecimal receivingCurrentBalance = receivingUserDefaultAccount.getAvailableBalance();
            BigDecimal receivingUpdatedBalance = receivingCurrentBalance.add(transferAmt);

            sendingUserDefaultAccount.setAvailableBalance(sendingUpdatedBalance);
            receivingUserDefaultAccount.setAvailableBalance(receivingUpdatedBalance);

            accountDAO.save(sendingUserDefaultAccount);
            accountDAO.save(receivingUserDefaultAccount);

        }
        transact.setStatus(status);

        return transact;

    }




}
