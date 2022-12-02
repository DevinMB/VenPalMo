package org.devinbutts.VenPalMo.controller;

import lombok.extern.slf4j.Slf4j;
import org.devinbutts.VenPalMo.dao.DisplayUserDAO;
import org.devinbutts.VenPalMo.dao.MessageDAO;
import org.devinbutts.VenPalMo.dao.UserDAO;
import org.devinbutts.VenPalMo.model.User;
import org.devinbutts.VenPalMo.model.dto.TransactDTO;
import org.devinbutts.VenPalMo.model.Message;
import org.devinbutts.VenPalMo.model.dto.UserDTO;
import org.devinbutts.VenPalMo.service.TransactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.PermitAll;
import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
public class MainController {

    @Autowired
    TransactService transactService;

    @Autowired
    MessageDAO messageDAO;

    @Autowired
    DisplayUserDAO displayUserDAO;

    @RequestMapping(value = {"/","/login.html"}, method = RequestMethod.GET)
    public ModelAndView slash() {
        log.debug("Main Controller Login Request");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");

        return modelAndView;
    }

    @RequestMapping(value = {"/welcome","/welcome.html"} ,  method = RequestMethod.GET)
    public ModelAndView welcome(Principal principal){
        log.debug("Main Controller Welcome Request");

        ModelAndView modelAndView  = new ModelAndView();
        modelAndView.setViewName("welcome");



        UserDTO loggedInUser = displayUserDAO.findUserByEmail(principal.getName());
        log.debug(loggedInUser.toString());

        List<TransactDTO> userTransactions = transactService.findTransactionsForDisplayByUserId(loggedInUser.getUserId());
        modelAndView.addObject("transactions",userTransactions);


        //TODO: Create MessageService and DTO to clean up data for chat
        List<Message> userMessages = messageDAO.findUserMessages(loggedInUser.getUserId());
        modelAndView.addObject("messages",userMessages);


        return modelAndView;
    }









}
