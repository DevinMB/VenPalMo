package org.devinbutts.VenPalMo.service;

import org.devinbutts.VenPalMo.dao.TransactDAO;
import org.devinbutts.VenPalMo.model.dto.TransactDTO;
import org.devinbutts.VenPalMo.model.Transact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class TransactService  {

    @Autowired
    TransactDAO transactDAO;

    public List<TransactDTO> findTransactionsForDisplayByUserId(Integer userId){

        List<Transact> transactions = transactDAO.findAllUserTransactions(userId);
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


}
