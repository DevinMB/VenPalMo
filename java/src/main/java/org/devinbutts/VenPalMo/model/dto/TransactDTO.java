package org.devinbutts.VenPalMo.model.dto;

import lombok.*;
import org.devinbutts.VenPalMo.model.User;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Transact DTO used for displaying transactions to user in a more useful manner.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"user"})
@ToString(exclude = {"user"})
public class TransactDTO {

    private Integer id;

    private Date createdDate;

    private User user;

    private String transactionType;

    private BigDecimal transactionAmount;

    private String currency;

    private String note;

    private String status;

}

