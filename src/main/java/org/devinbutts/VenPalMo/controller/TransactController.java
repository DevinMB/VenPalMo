package org.devinbutts.VenPalMo.controller;


import lombok.extern.slf4j.Slf4j;
import org.devinbutts.VenPalMo.dao.DisplayUserDAO;
import org.devinbutts.VenPalMo.dao.TransactDAO;
import org.devinbutts.VenPalMo.model.UserDTO;
import org.devinbutts.VenPalMo.model.Transact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Slf4j
public class TransactController {

    @Autowired
    TransactDAO transactDAO;

    @Autowired
    DisplayUserDAO displayUserDAO;

    @RequestMapping(value = {"/send","/send.html"} ,  method = RequestMethod.GET)
    public ModelAndView sendPage(){

        log.debug("Transact Controller Send Page");

        ModelAndView modelAndView  = new ModelAndView();
        modelAndView.setViewName("send");

        List<UserDTO> userList = displayUserDAO.findAll();

        modelAndView.addObject("users",userList);

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
