package VenPalMo.database.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@EqualsAndHashCode
@Getter
@Setter
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

    public TransactionStatus(String name) {
        this.name = name;
    }

}
