package VenPalMo.database.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@EqualsAndHashCode
@Getter
@Setter
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;

    private String street;

    private String city;

    private String state;

    private String zip;

    public Address() {
    }

    public Address(String street, String city, String state, String zip) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

}
