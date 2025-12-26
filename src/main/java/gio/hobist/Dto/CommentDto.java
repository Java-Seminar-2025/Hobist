package gio.hobist.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CommentDto {
    private UUID id;
    private UUID postId;
    private UUID userId;
    private String message;
    private Integer likeNumber;

    public CommentDto() {}

    public CommentDto(UUID id, UUID postId, UUID userId, String message, Integer likeNumber) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
        this.message = message;
        this.likeNumber = likeNumber;
    }
}