package org.devinbutts.VenPalMo.controller;

import lombok.extern.slf4j.Slf4j;
import org.devinbutts.VenPalMo.dao.DisplayUserDAO;
import org.devinbutts.VenPalMo.dao.TransactDAO;
import org.devinbutts.VenPalMo.model.dto.UserDTO;
import org.devinbutts.VenPalMo.model.Transact;
import org.devinbutts.VenPalMo.model.form.TransactForm;
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

//TODO: Build Send.html file
//TODO: Build Receive.html file
//TODO: Build Transaction.html file

@Controller
@Slf4j
public class TransactController {

    @Autowired
    TransactDAO transactDAO;

    @Autowired
    TransactService transactService;

    @Autowired
    DisplayUserDAO displayUserDAO;

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
    public ModelAndView sendSubmit(@ModelAttribute(value = "transactForm") @Valid TransactForm transactForm, BindingResult bindingResult){
        ModelAndView modelAndView  = new ModelAndView();
        log.debug("Send Transact Submitted");

        transactForm.setStatus("CLEARED");

        List<ObjectError> errors = bindingResult.getAllErrors();
        for (ObjectError e : errors) {
            log.debug(e.getDefaultMessage());
        }

        if (errors.size() > 0) {
            modelAndView.setViewName("/send/" + transactForm.getReceivingUserId());
        } else {

            Transact newTransact = transactService.createTransactionFromForm(transactForm);
            transactDAO.save(newTransact);

            //TODO: Decide if I want success page
            modelAndView.setViewName("redirect:/welcome");
            //Maybe use this to display success on welcome page...
            modelAndView.addObject("sent","true");

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
    public ModelAndView sendRequest(@ModelAttribute(value = "transactForm") @Valid TransactForm transactForm, BindingResult bindingResult){
        ModelAndView modelAndView  = new ModelAndView();
        log.debug("Request Transact Submitted");

        transactForm.setStatus("REQUESTED");

        List<ObjectError> errors = bindingResult.getAllErrors();
        for (ObjectError e : errors) {
            log.debug(e.getDefaultMessage());
        }

        if (errors.size() > 0) {
            modelAndView.setViewName("/request/" + transactForm.getReceivingUserId());
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

}
