package org.devinbutts.VenPalMo.model;

import lombok.*;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@EqualsAndHashCode(exclude = {"sendingUser", "receivingUser"})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"sendingUser","receivingUser"})
@Table(name = "transaction")
public class Transact {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "sending_user_id",insertable = false,updatable = false)
    private Integer sendingUserId;

    @Column(name = "receiving_user_id",insertable = false,updatable = false)
    private Integer receivingUserId;

    @ManyToOne()
    @JoinColumn(name = "sending_user_id")
    private User sendingUser;

    @ManyToOne()
    @JoinColumn(name = "receiving_user_id")
    private User receivingUser;

    @Column(name = "amount")
    private BigDecimal transactionAmount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "note")
    private String note;

    @Column(name = "status")
    private String status;

}
