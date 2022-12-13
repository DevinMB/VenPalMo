package org.devinbutts.VenPalMo.controller;

import lombok.extern.slf4j.Slf4j;
import org.devinbutts.VenPalMo.dao.DisplayUserDAO;
import org.devinbutts.VenPalMo.dao.TransactDAO;
import org.devinbutts.VenPalMo.dao.UserDAO;
import org.devinbutts.VenPalMo.model.Account;
import org.devinbutts.VenPalMo.model.User;
import org.devinbutts.VenPalMo.model.dto.TransactDTO;
import org.devinbutts.VenPalMo.model.dto.UserDTO;
import org.devinbutts.VenPalMo.model.Transact;
import org.devinbutts.VenPalMo.model.form.TransactForm;
import org.devinbutts.VenPalMo.service.AccountService;
import org.devinbutts.VenPalMo.service.TransactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

//TODO:Edit mapping to include /transaction/ at the front of every transaction request.

/**
 * Transaction controller is responsible for all requests to send or receive money @ /transact.
 */
@Controller
@Slf4j
@RequestMapping(value = "/transact")
public class TransactController {

    @Autowired
    TransactDAO transactDAO;

    @Autowired
    TransactService transactService;

    @Autowired
    DisplayUserDAO displayUserDAO;

    @Autowired
    UserDAO userDAO;

    @Autowired
    AccountService accountService;

    @RequestMapping(value = {"/send/{id}"} ,  method = RequestMethod.GET)
    public ModelAndView sendPage(@PathVariable int id, Principal principal){

        log.debug("Transact Controller Send Page");

        ModelAndView modelAndView  = new ModelAndView();
        modelAndView.setViewName("send_request");

        UserDTO receivingUser = displayUserDAO.findByUserId(id);
        UserDTO sendingUser = displayUserDAO.findUserByEmail(principal.getName());

        TransactForm transactForm = new TransactForm();
        transactForm.setReceivingUserId(id);
        transactForm.setSendingUserId(sendingUser.getId());

        modelAndView.addObject("receivingUser", receivingUser);
        modelAndView.addObject("sendingUser",sendingUser);
        modelAndView.addObject("transactForm",transactForm);
        modelAndView.addObject("pageTitle","Send Money");
        modelAndView.addObject("sendOrRequest","send");

        return modelAndView;
    }

    @RequestMapping(value={"/send"},method = RequestMethod.POST)
    public ModelAndView sendSubmit(@ModelAttribute(value = "transactForm") @Valid TransactForm transactForm, BindingResult bindingResult,Principal principal){

        ModelAndView modelAndView  = new ModelAndView();

        log.debug("Send Transact Submitted");

        User loggedInUser = userDAO.findByEmail(principal.getName());

        Account senderAcct = accountService.getDefaultAccount(loggedInUser);

        List<ObjectError> errors = bindingResult.getAllErrors();
        for (ObjectError e : errors) {
            log.debug(e.getDefaultMessage());
        }

        if (errors.size() > 0) {
//            modelAndView.setViewName("redirect:/send/" + transactForm.getReceivingUserId());
            modelAndView.setViewName("send_request");
            modelAndView.addObject("transactForm",transactForm);
            modelAndView.addObject("pageTitle","Send Money");
            modelAndView.addObject("sendOrRequest","send");
        } else {
            int compare = senderAcct.getAvailableBalance().compareTo(transactForm.getTransactionAmount());
            //Make sure user has enough money to send
            if(compare >= 0 ){
                //if you have enough money then set to cleared and proceed
                transactForm.setStatus("CLEARED");
                Transact newTransact = transactService.createTransactionFromForm(transactForm);
                transactDAO.save(newTransact);

                modelAndView.setViewName("redirect:/welcome");
                //Maybe use this to display success on welcome page...
                modelAndView.addObject("sent","true");
            }else{
                modelAndView.setViewName("send_request");
                modelAndView.addObject("noFunds","Not enough funds to send :(");
                modelAndView.addObject("transactForm",transactForm);
                modelAndView.addObject("pageTitle","Send Money");
                modelAndView.addObject("sendOrRequest","send");
            }
        }
        return modelAndView;
    }

    @RequestMapping(value = {"/request/{id}"} ,  method = RequestMethod.GET)
    public ModelAndView requestPage(@PathVariable int id, Principal principal){

        log.debug("Transact Controller Send Page");

        ModelAndView modelAndView  = new ModelAndView();
        modelAndView.setViewName("send_request");

        UserDTO sendingUser = displayUserDAO.findByUserId(id);
        UserDTO receivingUser = displayUserDAO.findUserByEmail(principal.getName());

        TransactForm transactForm = new TransactForm();

        transactForm.setReceivingUserId(receivingUser.getId());
        transactForm.setSendingUserId(sendingUser.getId());

        modelAndView.addObject("receivingUser", receivingUser);
        modelAndView.addObject("sendingUser",sendingUser);
        modelAndView.addObject("transactForm",transactForm);
        modelAndView.addObject("pageTitle","Request Money");
        modelAndView.addObject("sendOrRequest","request");

        return modelAndView;
    }

    @RequestMapping(value={"/request"},method = RequestMethod.POST)
    public ModelAndView requestSubmit(@ModelAttribute(value = "transactForm") @Valid TransactForm transactForm, BindingResult bindingResult){
        ModelAndView modelAndView  = new ModelAndView();
        log.debug("Request Transact Submitted");

        transactForm.setStatus("REQUESTED");

        List<ObjectError> errors = bindingResult.getAllErrors();
        for (ObjectError e : errors) {
            log.debug(e.getDefaultMessage());
        }

        if (errors.size() > 0) {
//            modelAndView.setViewName("redirect:/request/" + transactForm.getReceivingUserId());
            modelAndView.setViewName("send_request");
            modelAndView.addObject("pageTitle","Request Money");
            modelAndView.addObject("sendOrRequest","request");
        } else {

            Transact newTransact = transactService.createTransactionFromForm(transactForm);
            transactDAO.save(newTransact);

            //TODO: Decide if I want success page
            modelAndView.setViewName("redirect:/welcome");
            //Maybe use this to display success on welcome page...
            modelAndView.addObject("request","true");

        }
        return modelAndView;
    }

    @RequestMapping(value={"/pending"},method = RequestMethod.GET)
    public ModelAndView pendingTransactionsPage(Principal principal){

        log.debug("Pending Transaction Page Requested");

        ModelAndView modelAndView = new ModelAndView("transaction_requests");

        UserDTO loggedInUser = displayUserDAO.findUserByEmail(principal.getName());

        //get list of pending transactions
        List<TransactDTO> pendingTransacts = transactService.findRequestedTransactionsForDisplayByUserId(loggedInUser.getId());
        modelAndView.addObject("pendingTransactions",pendingTransacts);

        if(pendingTransacts.size()==1){
            modelAndView.addObject("pendingMessageToUser","Please see below pending request for payment from user:");
        }else{
            modelAndView.addObject("pendingMessageToUser","Please see below pending requests for payment from users:");
        }


        return modelAndView;

    }

    @RequestMapping(value={"/approve/{id}"},method = RequestMethod.GET)
    public ModelAndView approveTransaction(@PathVariable int id, Principal principal){
        ModelAndView modelAndView = new ModelAndView();

        //check to see if transaction id belongs to user
        UserDTO loggedInUser = displayUserDAO.findUserByEmail(principal.getName());

        List<TransactDTO> pendingTransacts = transactService.findRequestedTransactionsForDisplayByUserId(loggedInUser.getId());

        boolean allowToUpdate = true;

        for(TransactDTO t : pendingTransacts){
            if (t.getId() != id) {
                allowToUpdate = false;
                modelAndView.addObject("userPrivilegeError","User does not have permission to approve this transaction");
                break;
            }
        }

        if(!accountService.validateSendingFunds(loggedInUser,transactDAO.findByTransactId(id))){
            allowToUpdate = false;
            modelAndView.addObject("noFunds","Not enough funds to send that transaction :(");
        }

        if (allowToUpdate){
            Transact transact = transactDAO.findByTransactId(id);
            transactService.setStatus(transact,"CLEARED");
            transactDAO.save(transact);
            log.debug("User approved transaction");
            modelAndView.setViewName("redirect:/pending");
        }else{
            modelAndView.setViewName("transaction_requests");
            //get list of pending transactions
            modelAndView.addObject("pendingTransactions",pendingTransacts);
            if(pendingTransacts.size()==1){
                modelAndView.addObject("pendingMessageToUser","Please see below pending request for payment from user:");
            }else{
                modelAndView.addObject("pendingMessageToUser","Please see below pending requests for payment from users:");
            }


        }






        return modelAndView;
    }

    @RequestMapping(value={"/deny/{id}"},method = RequestMethod.GET)
    public ModelAndView denyTransaction(@PathVariable int id, Principal principal){
        //check to see if transaction id belongs to user
        UserDTO loggedInUser = displayUserDAO.findUserByEmail(principal.getName());

        List<TransactDTO> pendingTransacts = transactService.findRequestedTransactionsForDisplayByUserId(loggedInUser.getId());

        boolean allowToUpdate = false;

        for(TransactDTO t : pendingTransacts){
            if (t.getId() == id) {
                allowToUpdate = true;
                break;
            }
        }

        if (allowToUpdate){
            Transact transact = transactDAO.findByTransactId(id);
            transactService.setStatus(transact,"DENIED");
            transactDAO.save(transact);
            log.debug("User denied transaction");
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/pending");
        log.debug("User denied transaction");

        return modelAndView;
    }

}
