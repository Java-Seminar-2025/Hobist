package gio.hobist.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "hobby")
public class Hobby {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "id_user")
    private UUID idUser;

    private String name;

    @Lob
    private byte[] image;

    public Hobby() { super(); }

    public Hobby(UUID id, UUID idUser, String name, byte[] image) {
        this.id = id;
        this.idUser = idUser;
        this.name = name;
        this.image = image;
    }
}
