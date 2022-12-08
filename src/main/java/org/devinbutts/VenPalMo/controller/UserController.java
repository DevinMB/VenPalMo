package org.devinbutts.VenPalMo.controller;

import lombok.extern.slf4j.Slf4j;
import org.devinbutts.VenPalMo.dao.DisplayUserDAO;
import org.devinbutts.VenPalMo.dao.UserDAO;
import org.devinbutts.VenPalMo.model.dto.TransactDTO;
import org.devinbutts.VenPalMo.model.dto.UserDTO;
import org.devinbutts.VenPalMo.model.User;
import org.devinbutts.VenPalMo.model.form.EditUserForm;
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
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


//TODO: Implement better request mapping, use /User at base
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

    @RequestMapping(value = {"/search/send","/search/send_request.html"}, method = RequestMethod.GET)
    public ModelAndView searchForUserToSendTo() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");
        List<UserDTO> users = new ArrayList<>();
        UserDTO searchUser = new UserDTO();
        modelAndView.addObject("userDTO",searchUser);
        modelAndView.addObject("users", users);
        modelAndView.addObject("searchType", "send");

        return modelAndView;
    }

    @RequestMapping(value = {"/search/send","/search/send_request.html"}, method = RequestMethod.POST)
    public ModelAndView submitSendUserToSearch(@ModelAttribute(value="userDTO") UserDTO searchUser) {

        log.debug("Search User to Send Request " + searchUser.getFirstName() + " " + searchUser.getLastName() + " " + searchUser.getEmail());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");
        List<UserDTO> users = new ArrayList<>();
        users = displayUserDAO.findByFirstLastEmail("%" + searchUser.getFirstName() + "%", "%" + searchUser.getLastName() + "%", "%" + searchUser.getEmail() + "%");
        modelAndView.addObject("userDTO",searchUser);
        modelAndView.addObject("users", users);
        modelAndView.addObject("searchType", "send");

        return modelAndView;
    }

    @RequestMapping(value = {"/search/request","/search/request.html"}, method = RequestMethod.GET)
    public ModelAndView searchForUserToRequestFrom() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");
        List<UserDTO> users = new ArrayList<>();
        UserDTO searchUser = new UserDTO();
        modelAndView.addObject("userDTO",searchUser);
        modelAndView.addObject("users", users);
        modelAndView.addObject("searchType", "request");

        return modelAndView;
    }

    @RequestMapping(value = {"/search/request","/search/request.html"}, method = RequestMethod.POST)
    public ModelAndView submitSendUserToRequestFrom(@ModelAttribute(value="userDTO") UserDTO searchUser) {

        log.debug("Search User to Send Request " + searchUser.getFirstName() + " " + searchUser.getLastName() + " " + searchUser.getEmail());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");
        List<UserDTO> users = new ArrayList<>();
        users = displayUserDAO.findByFirstLastEmail("%" + searchUser.getFirstName() + "%", "%" + searchUser.getLastName() + "%", "%" + searchUser.getEmail() + "%");
        modelAndView.addObject("userDTO",searchUser);
        modelAndView.addObject("users", users);
        modelAndView.addObject("searchType", "request");

        return modelAndView;
    }

    @RequestMapping(value = {"/user/edit"}, method = RequestMethod.GET)
    public ModelAndView editUser(Principal principal) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit");

        User loggedInUser = userDAO.findByEmail(principal.getName());

        EditUserForm form = userService.createUserFromEditUserForm(loggedInUser);

        modelAndView.addObject("editUserForm", form);

        return modelAndView;
    }

    @RequestMapping(value = {"/user/edit"}, method = RequestMethod.POST)
    public ModelAndView editUserSubmit(Principal principal, @ModelAttribute(value = "editUserForm") @Valid EditUserForm form, BindingResult bindingResult ) {

        ModelAndView modelAndView = new ModelAndView();

        User loggedInUser = userDAO.findByEmail(principal.getName());

        List<ObjectError> errors = bindingResult.getAllErrors();

        if (errors.size() > 0) {
            modelAndView.setViewName("edit");
        } else {
            userService.updateUserFromForm(loggedInUser,form);
            modelAndView.setViewName("redirect:/welcome");
        }
        return modelAndView;
    }


}
