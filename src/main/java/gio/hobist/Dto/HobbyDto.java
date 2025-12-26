package gio.hobist.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class HobbyDto {
    private UUID id;
    private UUID idUser;
    private String name;
    private String image;
    private LocalDateTime createdAt;

    public HobbyDto() {}

    public HobbyDto(UUID id, UUID idUser, String name, String image) {
        this.id = id;
        this.idUser = idUser;
        this.name = name;
        this.image = image;
    }
}