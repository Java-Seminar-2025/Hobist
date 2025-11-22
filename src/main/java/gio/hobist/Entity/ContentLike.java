package gio.hobist.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "content_like")
public class ContentLike {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_post")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "id_comment")
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    public ContentLike() { super(); }
}
