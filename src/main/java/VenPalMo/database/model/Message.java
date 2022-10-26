package VenPalMo.database.model;


import javax.persistence.*;

@Entity
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

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }
}
