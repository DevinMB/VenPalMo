package org.devinbutts.VenPalMo.controller;


import org.devinbutts.VenPalMo.dao.TransactDAO;
import org.devinbutts.VenPalMo.model.Transact;
import org.devinbutts.VenPalMo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {

//    @Autowired
//    TransactDAO transactDAO;


    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView slash() {
        System.out.println("Index Controller Request");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");

        return modelAndView;
    }

    @RequestMapping(value = {"/welcome","/welcome.html"} ,  method = RequestMethod.GET)
    public ModelAndView welcome(){
        System.out.println("Index Controller Request");

        ModelAndView modelAndView  = new ModelAndView();
        modelAndView.setViewName("welcome");

        return modelAndView;
    }

    @RequestMapping(value = {"/register","/register.html"} ,  method = RequestMethod.GET)
    public ModelAndView register(){
        System.out.println("Index Controller Request");

        ModelAndView modelAndView  = new ModelAndView();
        modelAndView.setViewName("register");

        return modelAndView;
    }


}
