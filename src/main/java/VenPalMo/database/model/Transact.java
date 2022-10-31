package VenPalMo.database.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
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



    public Integer getId() {
        return id;
    }

    public User getSendingUser() {
        return sendingUser;
    }

    public void setSendingUser(User sendingUser) {
        this.sendingUser = sendingUser;
    }

    public User getReceivingUser() {
        return receivingUser;
    }

    public void setReceivingUser(User receivingUser) {
        this.receivingUser = receivingUser;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }
}
