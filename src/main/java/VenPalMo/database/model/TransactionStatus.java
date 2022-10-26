package VenPalMo.database.model;

import javax.persistence.*;

@Entity
@Table(name = "transaction_status")
public class TransactionStatus {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique=true)
    private String name;

    public TransactionStatus() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
