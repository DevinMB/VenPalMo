package VenPalMo.database.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@EqualsAndHashCode(exclude = {"address","accounts"})
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

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @Column(name = "joined_date")
    private LocalDate joinedDate;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @OneToMany(targetEntity = Account.class, cascade = {CascadeType.ALL})
    private List<Account> accounts;

    private String role;

    private boolean active;

    //constructors
    public User() {
    }

    public User(String firstName, String lastName, String email, String password, String phoneNumber, Address address, LocalDate joinedDate, LocalDate birthDate,  String role, boolean active) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.joinedDate = joinedDate;
        this.birthDate = birthDate;
        this.accounts = new ArrayList<Account>();
        this.role = role;
        this.active = active;
    }

    /**
     * Adds an account to the accounts list within a User.
     * @param newAccount
     */
    public void addAccount(Account newAccount) {
        accounts.add(newAccount);
    }

}
