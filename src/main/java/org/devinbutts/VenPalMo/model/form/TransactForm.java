package org.devinbutts.VenPalMo.model.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
public class TransactForm {

    private Date createdDate;

    private Integer sendingUserId;

    private Integer receivingUserId;

    private BigDecimal transactionAmount;

    private String currency;

    private String note;

    private String status;

}
