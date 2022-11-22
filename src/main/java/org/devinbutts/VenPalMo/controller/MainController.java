package org.devinbutts.VenPalMo.controller;


import org.devinbutts.VenPalMo.dao.TransactDAO;
import org.devinbutts.VenPalMo.model.Transact;
import org.devinbutts.VenPalMo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    TransactDAO transactDAO;

    @RequestMapping(value={"/"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Transact> findAllUsers(){
        List<Transact> transacts = transactDAO.findAll();
        return transacts;
    }




}
