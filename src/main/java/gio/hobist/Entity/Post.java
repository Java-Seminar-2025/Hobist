package gio.hobist.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "id_user")
    private UUID idUser;

    private String message;

    @Column(name = "image")
    private byte[] image;

    @Column(name = "like_number")
    private Integer likeNumber;

    @Column(name="created_at")
    private Timestamp createdAt;

}
