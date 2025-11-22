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
    private byte[] imageRawData;

    @Column(name = "like_number")
    private Integer likeNumber;

    public Post() { super(); }
}
