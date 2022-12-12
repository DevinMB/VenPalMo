package org.devinbutts.VenPalMo.service;

import lombok.extern.slf4j.Slf4j;
import org.devinbutts.VenPalMo.dao.AccountDAO;
import org.devinbutts.VenPalMo.model.Account;
import org.devinbutts.VenPalMo.model.Transact;
import org.devinbutts.VenPalMo.model.User;
import org.devinbutts.VenPalMo.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Account Service used primarily for validating if user has enough funds in account to process transaction.
 */
@Slf4j
@Service
public class AccountService {

    @Autowired
    AccountDAO accountDAO;

    public boolean validateSendingFunds(UserDTO userDTO, Transact transact){

        Account sendingAccount = null;

        try{
            sendingAccount = accountDAO.findDefaultAccountByUserId(userDTO.getId());
        }catch (Exception e){
            log.error(e.getMessage());
        }

        int compare = -1;

        if(sendingAccount!=null){
            compare = sendingAccount.getAvailableBalance().compareTo(transact.getTransactionAmount());
        }

        return compare >= 0;
    }

    public Account getDefaultAccount(User sendingUser) {
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
