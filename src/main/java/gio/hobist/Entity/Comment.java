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
public class Comment {//M.G: TODO: for M.G. fix comment table in db (problem: comment is not conneted to user!!)

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_post")
    private Post post;

    private String message;

    @Column(name = "like_number")
    private Integer likeNumber;

}
