package gio.hobist.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_post", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    private String content;

    private Integer likes;

    public Comment() { super(); }

    public Comment(Post post, User user, String content) {
        this.post = post;
        this.user = user;
        this.content = content;
        this.likes = 0;
    }
}
