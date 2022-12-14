package org.devinbutts.VenPalMo.controller;

import org.devinbutts.VenPalMo.dao.DisplayUserDAO;
import org.devinbutts.VenPalMo.dao.UserDAO;
import org.devinbutts.VenPalMo.model.User;
import org.devinbutts.VenPalMo.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.List;

/**
 * Admin controller endpoint controlls all admin actions. Users must have ROLE_ADMIN to access.
 * Function of this controller primarily includes promoting users to admin role and "deleting" users.
 * Users that are deleted will have their active column set to 0.
 */
@RolesAllowed("ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    DisplayUserDAO displayUserDAO;

    @Autowired
    UserDAO userDAO;

    @GetMapping
    public ModelAndView adminPage(Principal principal) {

        ModelAndView modelAndView = new ModelAndView("admin");

        List<UserDTO> userList = displayUserDAO.findAllExceptLoggedInUser(principal.getName());

        modelAndView.addObject("userList", userList);

        return modelAndView;

    }

    @GetMapping(value = "/promote/{id}")
    public ModelAndView promoteUser(Principal principal, @PathVariable int id) {

        ModelAndView modelAndView = new ModelAndView();

        User user = userDAO.findByUserId(id);

        user.setRole("ROLE_ADMIN");
        userDAO.save(user);

        modelAndView.setViewName("success");
        modelAndView.addObject("success_note", "User account has been promoted to admin.");
        modelAndView.addObject("redirect_location", "welcome");

        return modelAndView;

    }

    @RequestMapping(value = "/delete/{id}")
    public ModelAndView deleteUser(Principal principal, @PathVariable int id) {

        ModelAndView modelAndView = new ModelAndView();

        User user = userDAO.findByUserId(id);
        user.setActive(0);
        userDAO.save(user);

        modelAndView.setViewName("success");
        modelAndView.addObject("success_note", "User account has been deleted.");
        modelAndView.addObject("redirect_location", "welcome");

        return modelAndView;
    }


}
