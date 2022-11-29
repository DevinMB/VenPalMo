package org.devinbutts.VenPalMo.controller;

import lombok.extern.slf4j.Slf4j;
import org.devinbutts.VenPalMo.dao.DisplayUserDAO;
import org.devinbutts.VenPalMo.dao.UserDAO;
import org.devinbutts.VenPalMo.model.dto.UserDTO;
import org.devinbutts.VenPalMo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
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
    public String registerUser(@ModelAttribute(value = "user") User user){

//TODO: Fix Phone Not Pulling Through To Object
        user.setRole("USER");
        user.setActive(1);
        user.setJoinedDate(new Date());

        userDAO.save(user);


        return "login";
    }


    @ResponseBody
    @GetMapping(value="/display_users")
    public List<UserDTO> getAllUsers(){

        List<UserDTO> userDTOS = displayUserDAO.findAll();

        return userDTOS;
    }

    @RequestMapping(value = {"/search"}, method = RequestMethod.GET)
    public ModelAndView searchForUser(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email) {

        log.debug("Search Request " + firstName + " " + lastName + " " + email);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("search");

        List<UserDTO> users = new ArrayList<>();

        users = displayUserDAO.findByFirstLastEmail("%" + firstName + "%", "%" + lastName + "%","%" + email + "%");

        modelAndView.addObject("firstName", firstName);
        modelAndView.addObject("lastName", lastName);
        modelAndView.addObject("email", email);
        modelAndView.addObject("users", users);

        return modelAndView;
    }





}
