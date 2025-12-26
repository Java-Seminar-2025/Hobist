package gio.hobist.Service;

import gio.hobist.Dto.HobbyDto;
import gio.hobist.Entity.Hobby;
import gio.hobist.Repository.HobbyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.UUID;

@Service
public class HobbyService {
    @Autowired
    private HobbyRepository hobbyRepository;

    public HobbyDto getCurrentAutenthicatedHobby(UUID hobbyId) {
        var hobby = hobbyRepository.findById(hobbyId).orElse(null);

        if (hobby == null) {
            return null;
        }

        return new HobbyDto(
            hobby.getId(),
            hobby.getIdUser(),
            hobby.getName(),
            Base64.getEncoder().encodeToString(hobby.getImage())
        );
    }

    public HobbyDto updateHobby(UUID hobbyId, HobbyDto hobbyDto) {
        var hobby = hobbyRepository.findById(hobbyId).orElse(null);

        if (hobby == null) {
            return null;
        }

        hobby.setName(hobbyDto.getName());
        hobby.setImage(Base64.getDecoder().decode(hobbyDto.getImage()));

        hobbyRepository.save(hobby);

        return new HobbyDto(
            hobby.getId(),
            hobby.getIdUser(),
            hobby.getName(),
            Base64.getEncoder().encodeToString(hobby.getImage())
        );
    }

    public HobbyDto createHobby(HobbyDto hobbyDto) {
        var hobby = new Hobby(
            UUID.randomUUID(),
            hobbyDto.getIdUser(),
            hobbyDto.getName(),
            Base64.getDecoder().decode(hobbyDto.getImage())
        );

        hobbyRepository.save(hobby);

        return new HobbyDto(
            hobby.getId(),
            hobby.getIdUser(),
            hobby.getName(),
            Base64.getEncoder().encodeToString(hobby.getImage())
        );
    }

    public void deleteHobby(UUID hobbyId) {
        hobbyRepository.deleteById(hobbyId);
    }
}
