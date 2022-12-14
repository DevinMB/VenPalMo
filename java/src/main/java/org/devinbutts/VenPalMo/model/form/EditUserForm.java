package org.devinbutts.VenPalMo.model.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * Form used to edit user profile information. Sent to the User Service.
 */
@Getter
@Setter
@ToString
public class EditUserForm {

    @NotEmpty(message = "First name is required.")
    @Length(max = 45, message = "First name must be less than 45 characters.")
    private String firstName;

    @NotEmpty(message = "Last name is required.")
    @Length(max = 45, message = "Last name must be less than 45 characters.")
    private String lastName;

    //TODO: make this better
    @NotEmpty(message = "Phone number is required.")
    @Length(max = 10, message = "Phone number must be less than 11 digits.")
    @Length(min= 10,message = "Phone number must have at least 10 digits.")
    private String phoneNumber;

    @NotEmpty(message = "Address must not be blank.")
    @Length(max = 45, message = "Address must be less than 45 characters.")
    private String address;

    @NotEmpty(message = "City must not be blank.")
    @Length(max = 45, message = "City name must be less than 45 characters.")
    private String city;

    @NotEmpty(message = "State must not be blank.")
    @Length(max = 45, message = "State name must be less than 45 characters.")
    private String state;

    @NotEmpty(message = "Zip must not be blank.")
    private String zip;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

}
