package gio.hobist.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "friendship")
public class Friendship {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_user1")
    private User user1;

    @ManyToOne
    @JoinColumn(name = "id_user2")
    private User user2;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "date_of_befriending")
    private java.sql.Date dateOfBefriending;

    public Friendship() { super(); }
}
