package org.devinbutts.VenPalMo.model;


import lombok.*;
import org.springframework.data.util.Lazy;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@EqualsAndHashCode(exclude = {"toUser","fromUser"})
@ToString(exclude = {"toUser","fromUser"})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "message")
public class Message {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_user_id")
    private User toUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_user_id")
    private User fromUser;



}
