package org.devinbutts.VenPalMo.controller;

import lombok.extern.slf4j.Slf4j;
import org.devinbutts.VenPalMo.dao.DisplayUserDAO;
import org.devinbutts.VenPalMo.dao.UserDAO;
import org.devinbutts.VenPalMo.model.dto.UserDTO;
import org.devinbutts.VenPalMo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
public class UserController {

    @Autowired
    DisplayUserDAO displayUserDAO;

    @Autowired
    UserDAO userDAO;

    @Autowired
    @Qualifier("passwordEncoder")
    private PasswordEncoder passwordEncoder;



    @GetMapping(value = {"/register","/register.html"} )
    public ModelAndView registerPage(){

        log.debug("Main Controller Register Request");

        ModelAndView modelAndView  = new ModelAndView();
        modelAndView.setViewName("register");

        modelAndView.addObject("user", new User());

        return modelAndView;
    }


    @PostMapping(value={"/register","/register.html"})
    public ModelAndView createUser(@ModelAttribute(value = "user") @Valid User user, BindingResult bindingResult){


        ModelAndView modelAndView = new ModelAndView();

        boolean valid = false;

        List<ObjectError> errors = bindingResult.getAllErrors();

        for(ObjectError e : errors){
            log.debug(e.getDefaultMessage());
        }

        //TODO: Validate User

        if(errors.size()>0){
            modelAndView.setViewName("register");
        }else{
            modelAndView.setViewName("login");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole("USER");
            user.setActive(1);
            user.setJoinedDate(new Date());

            userDAO.save(user);

            modelAndView.setViewName("login");
        }

        return  modelAndView;




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
