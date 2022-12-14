package org.devinbutts.VenPalMo.service;

import lombok.extern.slf4j.Slf4j;
import org.devinbutts.VenPalMo.dao.UserDAO;
import org.devinbutts.VenPalMo.model.Account;
import org.devinbutts.VenPalMo.model.User;
import org.devinbutts.VenPalMo.model.dto.UserDTO;
import org.devinbutts.VenPalMo.model.form.EditUserForm;
import org.devinbutts.VenPalMo.model.form.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User service class allows for creation of users (passwords encrypted with BCrypt), updating user info, and providing basic welcome message details.
 */
@Slf4j
@Service
public class UserService {
    @Autowired
    UserDAO userDAO;
    @Autowired
    @Qualifier("passwordEncoder")
    private PasswordEncoder passwordEncoder;

    public User createUser(UserForm userForm) {

        User newUser = new User();

        newUser.setFirstName(userForm.getFirstName());
        newUser.setLastName(userForm.getLastName());
        newUser.setEmail(userForm.getEmail());
        newUser.setPassword(passwordEncoder.encode(userForm.getPassword()));
        newUser.setPhoneNumber(userForm.getPhoneNumber());
        newUser.setAddress(userForm.getAddress());
        newUser.setCity(userForm.getCity());
        newUser.setState(userForm.getState());
        newUser.setZip(userForm.getZip());
        newUser.setJoinedDate(new Date());
        newUser.setBirthDate(userForm.getBirthDate());
        newUser.setRole("ROLE_USER");
        newUser.setActive(1);
        Account newAccount = new Account();
        List<Account> accounts = new ArrayList<>();
        accounts.add(newAccount);
        newUser.setAccounts(accounts);

        return newUser;

    }

    public EditUserForm createEditForm(User user) {
        EditUserForm editUserForm = new EditUserForm();

        editUserForm.setFirstName(user.getFirstName());
        editUserForm.setLastName(user.getLastName());
        editUserForm.setPhoneNumber(user.getPhoneNumber());
        editUserForm.setAddress(user.getAddress());
        editUserForm.setCity(user.getCity());
        editUserForm.setState(user.getState());
        editUserForm.setZip(user.getZip());
        editUserForm.setBirthDate(user.getBirthDate());

        return editUserForm;
    }

    public User updateUser(User user, EditUserForm form) {

        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setPhoneNumber(form.getPhoneNumber());
        user.setAddress(form.getAddress());
        user.setCity(form.getCity());
        user.setState(form.getState());
        user.setZip(form.getZip());
        user.setBirthDate(form.getBirthDate());

        try{
            userDAO.save(user);
        }catch (Exception e){
            log.error(e.getMessage());
        }

        return user;
    }

    public String getWelcomeMessageForUser(UserDTO user) {
        int hour = LocalTime.now().getHour();

        if (hour >= 12 && hour < 18) {
            return "Good afternoon, " + user.getFirstName();
        } else if (hour >= 18) {
            return "Good evening, " + user.getFirstName();
        } else {
            return "Good morning, " + user.getFirstName();
        }
    }


}
