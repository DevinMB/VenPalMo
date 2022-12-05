package org.devinbutts.VenPalMo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.devinbutts.VenPalMo.model.User;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

//TODO: create save transact function
//TODO: create approve transact function, this will update transact to "cleared" and update the account balance


