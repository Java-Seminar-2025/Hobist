package gio.hobist.Dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class CommentDto {
    private UUID id;
    private UUID postId;
    private UUID userId;
    private String message;
    private Integer likeNumber;

}