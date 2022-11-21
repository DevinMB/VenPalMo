package org.devinbutts.VenPalMo.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@EqualsAndHashCode()
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "user")
public class User {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    private String password;

    @Column(name = "phone")
    private String phoneNumber;

    private String address;

    private String city;

    private String state;

    private String zip;

    @Column(name = "joined_date")
    private LocalDate joinedDate;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    private String role;

    private boolean active;

}
