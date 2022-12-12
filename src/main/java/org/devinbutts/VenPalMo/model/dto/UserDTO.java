package org.devinbutts.VenPalMo.model.dto;


import lombok.*;

import javax.persistence.*;

/**
 * User DTO used for displaying user list to user in a more useful manner.
 */
@Entity
@EqualsAndHashCode()
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "user")
public class UserDTO {



    @Column(name = "id")
    @Id
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;






}
