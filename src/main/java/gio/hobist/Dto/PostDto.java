package gio.hobist.Dto;

import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class PostDto {

    private UUID Id;
    private UUID UserId;
    private String message;
    private String rawImage;//dto will get base64-encoded string for display of image
    private Integer likeNumber;
    private Timestamp createdAt;
}
