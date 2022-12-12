package org.devinbutts.VenPalMo.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;


/**
 * Message DTO used for displaying messages to user in a more useful manner.
 */
@Getter
@Setter
@ToString
public class MessageDTO {

    private Integer id;

    private Date createdDate;

    private String text;

    private String email;

    private String name;

    private String messageType;

}
