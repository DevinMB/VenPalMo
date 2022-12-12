package org.devinbutts.VenPalMo.model.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

/**
 * Form for creating transactions. Sent to Transact Service to be created into Transact entity.
 */
@Getter
@Setter
@ToString
public class TransactForm {

    private Integer sendingUserId;

    private Integer receivingUserId;

    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal transactionAmount;

    private String note;

    private String status;

}
