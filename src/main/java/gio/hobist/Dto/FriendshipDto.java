package gio.hobist.Dto;

import gio.hobist.Enum.Status;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.UUID;

@Getter
@Setter
public class FriendshipDto {
    private UUID id;
    private UUID user1Id;
    private UUID user2Id;
    private Status status;
    private Date dateOfBefriending;

    public FriendshipDto() {}

    public FriendshipDto(UUID id, UUID user1Id, UUID user2Id, Status status, Date dateOfBefriending) {
        this.id = id;
        this.user1Id = user1Id;
        this.user2Id = user2Id;
        this.status = status;
        this.dateOfBefriending = dateOfBefriending;
    }
}