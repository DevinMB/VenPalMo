package org.devinbutts.VenPalMo.validation;

import org.apache.commons.lang3.StringUtils;
import org.devinbutts.VenPalMo.dao.DisplayUserDAO;
import org.devinbutts.VenPalMo.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailUniqueValidator implements ConstraintValidator<EmailUnique, String> {

   @Autowired
    private DisplayUserDAO displayUserDAO;

   @Override
    public void initialize(EmailUnique constraintAnnotation){}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
       if(StringUtils.isEmpty(value)){
           return true;
       }

       UserDTO displayUser = displayUserDAO.findUserByEmail(value);

       return ( displayUser == null);
    }





}