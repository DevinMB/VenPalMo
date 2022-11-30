package org.devinbutts.VenPalMo.model.dto;


import lombok.*;

import javax.persistence.*;

@Entity
@EqualsAndHashCode()
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "user")
public class UserDTO {



    @Column(name = "id")
    @Id
    private Integer userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;






}