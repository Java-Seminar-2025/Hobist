package gio.hobist.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
public class MessageDto {
    private UUID id;
    private UUID friendshipId;
    private UUID userId;
    private String message;
    private String fileName;
    private String fileBase64;
    private OffsetDateTime timeSent;

    public MessageDto() {}

    public MessageDto(UUID id, UUID friendshipId, UUID userId, String message, String fileName, String fileBase64, OffsetDateTime timeSent) {
        this.id = id;
        this.friendshipId = friendshipId;
        this.userId = userId;
        this.message = message;
        this.fileName = fileName;
        this.fileBase64 = fileBase64;
        this.timeSent = timeSent;
    }
}