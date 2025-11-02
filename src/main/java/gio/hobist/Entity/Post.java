package gio.hobist.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    private String content;

    private Integer likes;

    public Post() { super(); }

    public Post(User user, String content) {
        this.user = user;
        this.content = content;
        this.likes = 0;
    }
}
