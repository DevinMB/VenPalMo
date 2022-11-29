package org.devinbutts.VenPalMo.controller;


import lombok.extern.slf4j.Slf4j;
import org.devinbutts.VenPalMo.dao.MessageDAO;
import org.devinbutts.VenPalMo.model.TransactDTO;
import org.devinbutts.VenPalMo.model.Message;
import org.devinbutts.VenPalMo.service.TransactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
public class MainController {

    @Autowired
    TransactService transactService;

    @Autowired
    MessageDAO messageDAO;


    @RequestMapping(value = {"/","login.html"}, method = RequestMethod.GET)
    public ModelAndView slash() {
        log.debug("Main Controller Login Request");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");

        return modelAndView;
    }

    @RequestMapping(value = {"/welcome","/welcome.html"} ,  method = RequestMethod.GET)
    public ModelAndView welcome(){
        log.debug("Main Controller Welcome Request");

        ModelAndView modelAndView  = new ModelAndView();
        modelAndView.setViewName("welcome");

        //TODO: update this with actual logged in user id using Spring Principal
        List<TransactDTO> userTransactions = transactService.findTransactionsForDisplayByUserId(1);
        modelAndView.addObject("transactions",userTransactions);

        List<Message> userMessages = messageDAO.findUserMessages(1);
        modelAndView.addObject("messages",userMessages);

        return modelAndView;
    }

    @RequestMapping(value = {"/register","/register.html"} ,  method = RequestMethod.GET)
    public ModelAndView register(){

        log.debug("Main Controller Register Request");

        ModelAndView modelAndView  = new ModelAndView();
        modelAndView.setViewName("register");

        return modelAndView;
    }







}
