package org.devinbutts.VenPalMo.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Account holds balance for a user, many accounts can be tied to a single user.
 */
@Entity
@EqualsAndHashCode(exclude = {"user"})
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"user"})
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

    @Column(name = "currency")
    private String currency;

    @Column(name = "balance")
    private BigDecimal availableBalance;

    @Column(name = "default_acct")
    private Integer defaultAccount;


}