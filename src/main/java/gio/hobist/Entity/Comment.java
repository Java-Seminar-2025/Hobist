package gio.hobist.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_post")
    private Post post;

    private String message;

    @Column(name = "like_number")
    private Integer likeNumber;

    public Comment(Comment c){
        this.id=c.id;
        this.post=c.post;
        this.message=c.message;
        this.likeNumber=c.likeNumber;
    }
}


