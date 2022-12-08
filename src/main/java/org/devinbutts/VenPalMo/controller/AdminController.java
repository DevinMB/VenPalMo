package org.devinbutts.VenPalMo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;

@Controller
@RequestMapping("/admin")
@RolesAllowed("ROLE_ADMIN")
public class AdminController {

    @RequestMapping
    public ModelAndView adminPage(Principal principal){

        ModelAndView modelAndView = new ModelAndView("admin");




        return modelAndView;


    }





}
