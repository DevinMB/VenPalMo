package VenPalMo.database.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@EqualsAndHashCode(exclude = {"toUser","fromUser"})
@Getter
@Setter
@Table(name = "message")
public class Message {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String text;

    @ManyToOne
    private User toUser;

    @ManyToOne
    private User fromUser;

    public Message() {
    }

    public Message(String text, User toUser, User fromUser) {
        this.text = text;
        this.toUser = toUser;
        this.fromUser = fromUser;
    }

}
