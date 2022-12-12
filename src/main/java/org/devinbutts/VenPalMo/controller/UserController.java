package org.devinbutts.VenPalMo.controller;

import lombok.extern.slf4j.Slf4j;
import org.devinbutts.VenPalMo.dao.DisplayUserDAO;
import org.devinbutts.VenPalMo.dao.UserDAO;
import org.devinbutts.VenPalMo.model.dto.UserDTO;
import org.devinbutts.VenPalMo.model.User;
import org.devinbutts.VenPalMo.model.form.EditUserForm;
import org.devinbutts.VenPalMo.model.form.UserForm;
import org.devinbutts.VenPalMo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value="/user")
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
            User newUser = userService.createUser(userForm);
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

        ModelAndView modelAndView = getSearchModelAndView();
        modelAndView.addObject("searchType", "send");

        return modelAndView;
    }

    @RequestMapping(value = {"/search/send","/search/send_request.html"}, method = RequestMethod.POST)
    public ModelAndView submitSendUserToSearch(@ModelAttribute(value="userDTO") UserDTO searchUser, Principal principal) {

        ModelAndView modelAndView = getSearchSubmitModelAndView(searchUser,principal );
        modelAndView.addObject("searchType", "send");

        return modelAndView;
    }

    @RequestMapping(value = {"/search/request","/search/request.html"}, method = RequestMethod.GET)
    public ModelAndView searchForUserToRequestFrom() {

        ModelAndView modelAndView = getSearchModelAndView();
        modelAndView.addObject("searchType", "request");

        return modelAndView;
    }

    @RequestMapping(value = {"/search/request","/search/request.html"}, method = RequestMethod.POST)
    public ModelAndView submitSendUserToRequestFrom(@ModelAttribute(value="userDTO") UserDTO searchUser, Principal principal) {

        ModelAndView modelAndView = getSearchSubmitModelAndView(searchUser, principal);
        modelAndView.addObject("searchType", "request");

        return modelAndView;
    }

    @RequestMapping(value = {"/edit"}, method = RequestMethod.GET)
    public ModelAndView editUser(Principal principal) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit");

        User loggedInUser = userDAO.findByEmail(principal.getName());

        EditUserForm form = userService.createEditForm(loggedInUser);

        modelAndView.addObject("editUserForm", form);

        return modelAndView;
    }

    @RequestMapping(value = {"/edit"}, method = RequestMethod.POST)
    public ModelAndView editUserSubmit(Principal principal, @ModelAttribute(value = "editUserForm") @Valid EditUserForm form, BindingResult bindingResult ) {

        ModelAndView modelAndView = new ModelAndView();

        User loggedInUser = userDAO.findByEmail(principal.getName());

        List<ObjectError> errors = bindingResult.getAllErrors();

        if (errors.size() > 0) {
            modelAndView.setViewName("edit");
            //TODO:add error object?
        } else {
            userService.updateUser(loggedInUser,form);
            modelAndView.setViewName("redirect:/welcome");
        }
        return modelAndView;
    }


    @RequestMapping(value = {"/search/message","/search/message.html"}, method = RequestMethod.GET)
    public ModelAndView searchForUserToMessage() {

        ModelAndView modelAndView = getSearchModelAndView();
        modelAndView.addObject("searchType", "message");

        return modelAndView;
    }

    @RequestMapping(value = {"/search/message","/search/message.html"}, method = RequestMethod.POST)
    public ModelAndView submitSendUserToMessagePage(@ModelAttribute(value="userDTO") UserDTO searchUser,Principal principal) {

        ModelAndView modelAndView = getSearchSubmitModelAndView(searchUser, principal);
        modelAndView.addObject("searchType", "message");

        return modelAndView;
    }


    private ModelAndView getSearchModelAndView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");
        List<UserDTO> users = new ArrayList<>();
        UserDTO searchUser = new UserDTO();
        modelAndView.addObject("userDTO",searchUser);
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    private ModelAndView getSearchSubmitModelAndView(UserDTO searchUser,Principal principal) {
        log.debug("Search User to Send Request " + searchUser.getFirstName() + " " + searchUser.getLastName() + " " + searchUser.getEmail());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");
        List<UserDTO> users = new ArrayList<>();

        //TODO: make a new method that only returns active users and users that are not you
        users = displayUserDAO.findByFirstLastEmail("%" + searchUser.getFirstName() + "%", "%" + searchUser.getLastName() + "%", "%" + searchUser.getEmail() + "%",principal.getName());

        modelAndView.addObject("userDTO", searchUser);
        modelAndView.addObject("users", users);
        return modelAndView;
    }

}
