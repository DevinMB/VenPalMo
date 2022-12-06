package org.devinbutts.VenPalMo.model.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.devinbutts.VenPalMo.validation.EmailUnique;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Getter
@Setter
@ToString
public class UserForm {

    @NotEmpty(message = "First name is required.")
    @Length(max = 45, message = "First name must be less than 45 characters.")
    private String firstName;

    @NotEmpty(message = "Last name is required.")
    @Length(max = 45, message = "Last name must be less than 45 characters.")
    private String lastName;

    @Length(max = 45, message = "Email name must be less than 45 characters.")
    @Email(message = "Please provide a valid email.")
    @NotEmpty(message = "Please provide an email.")
    @EmailUnique(message = "Email is already in database.")
    private String email;

    @Length(min = 8, message = "Password must be more than 8 characters.")
    @Length(max = 25, message = "Password must be less than 25 characters.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#\\$%\\^&\\*])(?=.*[A-Z])(?=.*[a-z]).{8,}$", message = "Password must contain a number, special character, and lower/uppercase letters.")
    @NotEmpty(message = "Password is required.")
    private String password;

    //TODO: Implement Custom Validation for confirm password
    @NotEmpty(message = "Password is required.")
    private String confirmPassword;

    @NotEmpty(message = "Phone number is required.")
    @Length(max = 45, message = "Phone number must be less than 45 characters.")
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
    private Date joinedDate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

}
