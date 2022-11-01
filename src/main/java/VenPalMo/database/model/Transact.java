package VenPalMo.database.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@EqualsAndHashCode(exclude = {"sendingUser", "receivingUser", "message", "status"})
@Getter
@Setter
@Table(name = "transaction")
public class Transact {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private User sendingUser;

    @ManyToOne
    private User receivingUser;

    @Column(name = "transaction_amount")
    private BigDecimal transactionAmount;

    @ManyToOne
    private Currency currency;

    @ManyToOne
    private Message message;

    @ManyToOne
    private TransactionStatus status;

    public Transact(User sendingUser, User receivingUser, BigDecimal transactionAmount, Currency currency, Message message, TransactionStatus status) {
        this.sendingUser = sendingUser;
        this.receivingUser = receivingUser;
        this.transactionAmount = transactionAmount;
        this.currency = currency;
        this.message = message;
        this.status = status;
    }

    public Transact() {
    }


}
