package org.devinbutts.VenPalMo.service;

import org.devinbutts.VenPalMo.model.User;
import org.springframework.web.bind.annotation.ModelAttribute;
import java.util.ArrayList;
import java.util.List;

public class UserService {


    //TODO: Not actually needed anymore due to spring bean form implementation
    public List<ModelAttribute> validateUser(User user){
        List<ModelAttribute> errors = new ArrayList<>();




        return errors;
    }



}
