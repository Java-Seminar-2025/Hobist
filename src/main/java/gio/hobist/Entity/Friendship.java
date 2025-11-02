package gio.hobist.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.Type;

import java.util.Map;

@Entity
@Getter
@Setter
@Table(name = "friendship")
@Check(constraints = "id_requesting_user < id_reciving_user")
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_requesting_user")
    private User requestingUser;

    @ManyToOne
    @JoinColumn(name = "id_reciving_user")
    private User recivingUser;

    @Enumerated(EnumType.STRING)
    private Status status = Status.pending;

    @Column(columnDefinition = "jsonb")
    private String chat;

    public Friendship() { super(); }

    public Friendship(User requester, User receiver, Status status) {
        this.requestingUser = requester;
        this.recivingUser = receiver;
        this.status = status;
    }

    public enum Status {
        pending, accepted, blocked
    }
}
