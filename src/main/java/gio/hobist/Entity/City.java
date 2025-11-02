package gio.hobist.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_country")
    private Country country;

    private String name;

    public City() { super(); }

    public City(Country country, String name) {
        this.country = country;
        this.name = name;
    }
}
