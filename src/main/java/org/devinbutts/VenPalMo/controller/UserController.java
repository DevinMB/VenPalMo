package org.devinbutts.VenPalMo.controller;

import lombok.extern.slf4j.Slf4j;
import org.devinbutts.VenPalMo.dao.DisplayUserDAO;
import org.devinbutts.VenPalMo.dao.UserDAO;
import org.devinbutts.VenPalMo.model.DisplayUser;
import org.devinbutts.VenPalMo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value="/user")
public class UserController {

    @Autowired
    DisplayUserDAO displayUserDAO;

    @Autowired
    UserDAO userDAO;

    @PostMapping(value="/register")
    public String registerUser(@ModelAttribute("user") User user, ModelMap model){

            model.addAttribute("firstName", user.getFirstName());
            model.addAttribute("lastName",user.getLastName());

        return "login";
    }


    @ResponseBody
    @GetMapping(value="/display_users")
    public List<DisplayUser> getAllUsers(){

        List<DisplayUser> displayUsers = displayUserDAO.findAll();

        return displayUsers;
    }







}
