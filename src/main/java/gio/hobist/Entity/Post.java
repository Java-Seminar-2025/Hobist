package gio.hobist.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue
    private UUID id;

    private String message;

    @Column(name = "image_raw_data")
    private byte[] imageRawData; // promjeni da ima tekst objave i da ima sliku(kao path za sliku u bazi), @{${post.imagePath}} da mogu ovo i ovo: ${post.text}

    @Column(name = "like_number")
    private Integer likeNumber;

    public Post() { super(); }
}
