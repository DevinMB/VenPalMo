package org.devinbutts.VenPalMo.controller;

import lombok.extern.slf4j.Slf4j;
import org.devinbutts.VenPalMo.dao.DisplayUserDAO;
import org.devinbutts.VenPalMo.dao.TransactDAO;
import org.devinbutts.VenPalMo.model.dto.UserDTO;
import org.devinbutts.VenPalMo.model.Transact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

//TODO: Build Send.html file
//TODO: Build Receive.html file
//TODO: Build Transaction.html file

@Controller
@Slf4j
public class TransactController {

    @Autowired
    TransactDAO transactDAO;

    @Autowired
    DisplayUserDAO displayUserDAO;

    @RequestMapping(value = {"/send/{id}"} ,  method = RequestMethod.GET)
    public ModelAndView sendPage(@PathVariable int id, Principal principal){

        log.debug("Transact Controller Send Page");

        ModelAndView modelAndView  = new ModelAndView();
        modelAndView.setViewName("send");

        UserDTO recievingUser = displayUserDAO.findById(id);

        UserDTO sendingUser = displayUserDAO.findUserByEmail(principal.getName());

        Transact newTransact = new Transact();

        modelAndView.addObject("recievingUser", recievingUser);
        modelAndView.addObject("sendingUser",sendingUser);
        
        //TODO: convert this to a transactionFORM, dont want to make the same mistakes as the user form...
        modelAndView.addObject("transaction",newTransact);



//        //TODO: FILL OUT NEW TRANSACTION
//        newTransact.set
//

        return modelAndView;
    }

    @RequestMapping(value={"/send/submit","send/submit.html"},method = RequestMethod.POST)
    public ModelAndView sendSubmit(@RequestBody(required = true)Transact transact){

        log.debug("Send Transact Submitted");

        ModelAndView modelAndView  = new ModelAndView();
        modelAndView.setViewName("welcome");

        transactDAO.save(transact);
        modelAndView.addObject("sent","true");

        //TODO: update this with actual logged in user id using Spring Principal
        List<Transact> userTransactions = transactDAO.findAllUserTransactions(2);

        modelAndView.addObject("transactions",userTransactions);

        return modelAndView;

    }






}
