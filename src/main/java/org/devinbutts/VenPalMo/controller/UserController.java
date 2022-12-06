package org.devinbutts.VenPalMo.controller;

import lombok.extern.slf4j.Slf4j;
import org.devinbutts.VenPalMo.dao.DisplayUserDAO;
import org.devinbutts.VenPalMo.dao.UserDAO;
import org.devinbutts.VenPalMo.model.dto.TransactDTO;
import org.devinbutts.VenPalMo.model.dto.UserDTO;
import org.devinbutts.VenPalMo.model.User;
import org.devinbutts.VenPalMo.model.form.UserForm;
import org.devinbutts.VenPalMo.service.UserService;
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
    UserService userService;


    @GetMapping(value = {"/register", "/register.html"})
    public ModelAndView registerPage() {

        log.debug("Main Controller Register Request");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");

        modelAndView.addObject("userForm", new UserForm());

        return modelAndView;
    }


    @PostMapping(value = {"/register", "/register.html"})
    public ModelAndView createUser(@ModelAttribute(value = "userForm") @Valid UserForm userForm, BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView();

        List<ObjectError> errors = bindingResult.getAllErrors();

        for (ObjectError e : errors) {
            log.debug(e.getDefaultMessage());
        }

        if (errors.size() > 0) {
            modelAndView.setViewName("register");
        } else {
            User newUser = userService.createUserFromForm(userForm);
            userDAO.save(newUser);
            modelAndView = registerSuccessPage(modelAndView);
        }
        return modelAndView;
    }

    public ModelAndView registerSuccessPage(ModelAndView modelAndView) {

        log.debug("Register Success Request");
        modelAndView.setViewName("success");
        modelAndView.addObject("success_note", "User account has been created.");
        modelAndView.addObject("redirect_location", "login");

        return modelAndView;
    }


    @ResponseBody
    @GetMapping(value = "/display_users")
    public List<UserDTO> getAllUsers() {

        List<UserDTO> userDTOS = displayUserDAO.findAll();

        return userDTOS;
    }

    @RequestMapping(value = {"/search/send","/search/send.html"}, method = RequestMethod.GET)
    public ModelAndView searchForUserToSendTo() {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("search");

        List<UserDTO> users = new ArrayList<>();
        UserDTO searchUser = new UserDTO();

        modelAndView.addObject("userDTO",searchUser);

        modelAndView.addObject("users", users);

        modelAndView.addObject("searchType", "SEND");

        return modelAndView;

    }


    //TODO: create universal search page that will redirect to send page form or request page form depending on which button was clicked
    @RequestMapping(value = {"/search/send","/search/send.html"}, method = RequestMethod.POST)
    public ModelAndView submitSendUserToSearch(@ModelAttribute(value="userDTO") UserDTO searchUser) {

        log.debug("Search User to Send Request " + searchUser.getFirstName() + " " + searchUser.getLastName() + " " + searchUser.getEmail());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("search");

        List<UserDTO> users = new ArrayList<>();

        users = displayUserDAO.findByFirstLastEmail("%" + searchUser.getFirstName() + "%", "%" + searchUser.getLastName() + "%", "%" + searchUser.getEmail() + "%");

        modelAndView.addObject("userDTO",searchUser);

        modelAndView.addObject("users", users);

        modelAndView.addObject("searchType", "SEND");

        return modelAndView;

    }

}
