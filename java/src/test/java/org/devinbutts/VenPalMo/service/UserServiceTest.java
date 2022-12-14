package org.devinbutts.VenPalMo.service;

import org.devinbutts.VenPalMo.model.User;
import org.devinbutts.VenPalMo.model.form.UserForm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    protected void createUserFromForm(){

        //create form

        UserForm userForm = new UserForm();

        userForm.setPassword("test");
        userForm.setAddress("123 test");
        userForm.setJoinedDate(new Date());
        userForm.setCity("TEST");
        userForm.setState("TI");
        userForm.setZip("49534");
        userForm.setBirthDate(new Date());
        userForm.setEmail("test@gmail.com");
        userForm.setFirstName("Devin");
        userForm.setLastName("Test");
        userForm.setPhoneNumber("2485559369");

        //created expected result

        User expectedUser = new User();

        expectedUser.setPassword("test");
        expectedUser.setAddress("123 test");
        expectedUser.setJoinedDate(userForm.getJoinedDate());
        expectedUser.setCity("TEST");
        expectedUser.setState("TI");
        expectedUser.setZip("49534");
        expectedUser.setBirthDate(userForm.getBirthDate());
        expectedUser.setEmail("test@gmail.com");
        expectedUser.setFirstName("Devin");
        expectedUser.setLastName("Test");
        expectedUser.setPhoneNumber("2485559369");


        //invoke

        User testUser = userService.createUser(userForm);

        //assert

        Assertions.assertEquals(expectedUser.getEmail(),testUser.getEmail());
        Assertions.assertEquals(expectedUser.getBirthDate(),testUser.getBirthDate());
        Assertions.assertEquals(expectedUser.getFirstName(),testUser.getFirstName());
        Assertions.assertEquals(expectedUser.getId(),testUser.getId());
        Assertions.assertEquals(expectedUser.getAddress(),testUser.getAddress());




    }



}
