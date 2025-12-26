package gio.hobist.Controller;

import gio.hobist.Dto.FriendshipDto;
import gio.hobist.Service.FriendshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/friendship")
@RequiredArgsConstructor
public class FriendshipControler {
    private final FriendshipService friendshipService;

    @GetMapping("/{friendshipId}")
    public ResponseEntity<FriendshipDto> getFriendship(@PathVariable UUID friendshipId) {
        FriendshipDto friendship = friendshipService.getCurrentAutenthicatedFriendship(friendshipId);
        return friendship != null ? ResponseEntity.ok(friendship) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{friendshipId}")
    public ResponseEntity<FriendshipDto> updateFriendship(@PathVariable UUID friendshipId, @RequestBody FriendshipDto friendshipDto) {
        FriendshipDto updatedFriendship = friendshipService.updateFriendship(friendshipId, friendshipDto);
        return updatedFriendship != null ? ResponseEntity.ok(updatedFriendship) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<FriendshipDto> createFriendship(@RequestBody FriendshipDto friendshipDto) {
        FriendshipDto createdFriendship = friendshipService.createFriendship(friendshipDto);
        return ResponseEntity.ok(createdFriendship);
    }

    @DeleteMapping("/{friendshipId}")
    public ResponseEntity<Void> deleteFriendship(@PathVariable UUID friendshipId) {
        friendshipService.deleteFriendship(friendshipId);
        return ResponseEntity.noContent().build();
    }
}
