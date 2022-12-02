package org.devinbutts.VenPalMo.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
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


    public Boolean getActive() {
        return this.active == 1;
    }
}
