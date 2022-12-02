package org.devinbutts.VenPalMo.service;

import org.devinbutts.VenPalMo.model.Account;
import org.devinbutts.VenPalMo.model.User;
import org.devinbutts.VenPalMo.model.form.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    @Qualifier("passwordEncoder")
    private PasswordEncoder passwordEncoder;

    public User createUserFromForm(UserForm userForm) {

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


}
