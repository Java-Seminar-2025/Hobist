package gio.hobist.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "hobby_user")
@IdClass(HobbyUserId.class)
public class HobbyUser {

    @Id
    @Column(name = "id_user")
    private UUID uid;

    @Id
    @Column(name = "id_hobby")
    private UUID hobbyId;

    public HobbyUser() { super(); }
}
