package gio.hobist.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "hobby")
public class Hobby {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    public Hobby() { super(); }

    public Hobby(String name) {
        this.name = name;
    }
}
