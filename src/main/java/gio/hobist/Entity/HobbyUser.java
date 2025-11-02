package gio.hobist.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "hobby_user")
public class HobbyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_hobby")
    private Hobby hobby;

    public HobbyUser() { super(); }

    public HobbyUser(User user, Hobby hobby) {
        this.user = user;
        this.hobby = hobby;
    }
}
