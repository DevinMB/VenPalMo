package org.devinbutts.VenPalMo.model.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;


@Getter
@Setter
@ToString
public class TransactForm {

    private Integer sendingUserId;

    private Integer receivingUserId;

    private BigDecimal transactionAmount;

    private String note;

    private String status;

}
