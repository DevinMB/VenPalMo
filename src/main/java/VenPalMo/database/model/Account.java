package VenPalMo.database.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@EqualsAndHashCode(exclude = {"user"})
@Getter
@Setter
@Table(name = "account")
public class Account {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Currency currency;

    private BigDecimal balance;

    @Column(name = "default_acct")
    private boolean defaultAccount;

    public Account() {
    }

    public Account(User user, Currency currency, BigDecimal balance, boolean defaultAccount) {
        this.user = user;
        this.currency = currency;
        this.balance = balance;
        this.defaultAccount = defaultAccount;
    }

}
