package org.devinbutts.VenPalMo.service;

import org.devinbutts.VenPalMo.dao.AccountDAO;
import org.devinbutts.VenPalMo.model.Account;
import org.devinbutts.VenPalMo.model.Transact;
import org.devinbutts.VenPalMo.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    AccountDAO accountDAO;

    public boolean validateSendingFunds(UserDTO userDTO, Transact transact){

        Account sendingAccount = accountDAO.findDefaultAccountByUserId(userDTO.getId());
        Integer compare = sendingAccount.getAvailableBalance().compareTo(transact.getTransactionAmount());

        return compare >= 0;
    }




}
