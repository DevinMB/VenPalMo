package VenPalMo.database.model;

import javax.persistence.*;

@Entity
@Table(name = "currency")
public class Currency {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String name;

    public Currency() {
    }

    public Currency(String name) {
        this.name = name;
    }



    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
