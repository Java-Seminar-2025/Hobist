package gio.hobist.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_post")
    private Post post;

    @Column(name = "id_user")
    private UUID userId;

    private String message;

    @Column(name = "like_number")
    private Integer likeNumber;

    public Comment() { super(); }
}
