package gio.hobist.Controller;

import gio.hobist.Dto.HobbyDto;
import gio.hobist.Service.HobbyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/hobby")
@RequiredArgsConstructor

public class HobbyControler {
    private final HobbyService hobbyService;

    @GetMapping("/{hobbyId}")
    public ResponseEntity<HobbyDto> getHobby(@PathVariable UUID hobbyId) {
        HobbyDto hobby = hobbyService.getCurrentAutenthicatedHobby(hobbyId);
        return hobby != null ? ResponseEntity.ok(hobby) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{hobbyId}")
    public ResponseEntity<HobbyDto> updateHobby(@PathVariable UUID hobbyId, @RequestBody HobbyDto hobbyDto) {
        HobbyDto updatedHobby = hobbyService.updateHobby(hobbyId, hobbyDto);
        return updatedHobby != null ? ResponseEntity.ok(updatedHobby) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<HobbyDto> createHobby(@RequestBody HobbyDto hobbyDto) {
        HobbyDto createdHobby = hobbyService.createHobby(hobbyDto);
        return ResponseEntity.ok(createdHobby);
    }

    @DeleteMapping("/{hobbyId}")
    public ResponseEntity<Void> deleteHobby(@PathVariable UUID hobbyId) {
        hobbyService.deleteHobby(hobbyId);
        return ResponseEntity.noContent().build();
    }
}
