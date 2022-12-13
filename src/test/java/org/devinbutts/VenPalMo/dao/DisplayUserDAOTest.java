package org.devinbutts.VenPalMo.dao;


import org.devinbutts.VenPalMo.model.Account;
import org.devinbutts.VenPalMo.model.dto.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class DisplayUserDAOTest {


    @Autowired
    DisplayUserDAO displayUserDAO;

    @Sql({"/database/test_data.sql"})
    @ParameterizedTest()
    @CsvFileSource(resources = {"/test/user.csv"},delimiter = ',',numLinesToSkip = 1)
    protected void getAllDisplayUsers(Integer id, String first_name, String last_name, String email, String password, String phone, String address, String city, String state, String zip, String joined_date, String birth_date,String  role,Integer active) {
        UserDTO expectedUser = new UserDTO();
        expectedUser.setId(id);
        expectedUser.setLastName(last_name);
        expectedUser.setFirstName(first_name);
        expectedUser.setEmail(email);

        List<UserDTO> displayUsersTest = displayUserDAO.findAll();


        UserDTO testUser = new UserDTO();

        for(UserDTO u: displayUsersTest){
            if(u.getId()==expectedUser.getId()){
                testUser = u;
            }
        }

        Assertions.assertEquals(expectedUser, testUser);


    }

}
