package org.devinbutts.VenPalMo.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@EqualsAndHashCode()
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "accounts")
@Table(name = "user")
public class User {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "First name is required.")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "Last name is required.")
    @Column(name = "last_name")
    private String lastName;

    @NotEmpty(message = "Email must not be blank.")
    private String email;

    @NotEmpty(message = "Password is required")
    private String password;

    @Transient
    private String confirmPassword;

    @Column(name = "phone")
    @NotEmpty(message = "Phone number is required.")
    private String phoneNumber;

    @NotEmpty(message = "Address must not be blank.")
    private String address;

    @NotEmpty(message = "City must not be blank.")
    private String city;

    @NotEmpty(message = "State must not be blank.")
    private String state;

    @NotEmpty(message = "Zip must not be blank.")
    private String zip;

    @Column(name = "joined_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date joinedDate;


    @Column(name = "birth_date")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    private String role;

    private Integer active;

    @OneToMany(mappedBy = "userId")
    private List<Account> accounts;






}
