package gio.hobist.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "country")
public class Country {

    @Id
    private Integer id;

    private String name;

    public Country() { super(); }

    public Country(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
