package gio.hobist.Controller;

import gio.hobist.Dto.MessageDto;
import gio.hobist.Service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
public class MessageControler {
    private final MessageService messageService;

    @GetMapping("/{messageId}")
    public ResponseEntity<MessageDto> getMessage(@PathVariable UUID messageId) {
        MessageDto message = messageService.getCurrentAutenthicatedMessage(messageId);
        return message != null ? ResponseEntity.ok(message) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{messageId}")
    public ResponseEntity<MessageDto> updateMessage(@PathVariable UUID messageId, @RequestBody MessageDto messageDto) {
        MessageDto updatedMessage = messageService.updateMessage(messageId, messageDto);
        return updatedMessage != null ? ResponseEntity.ok(updatedMessage) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<MessageDto> createMessage(@RequestBody MessageDto messageDto) {
        MessageDto createdMessage = messageService.createMessage(messageDto);
        return ResponseEntity.ok(createdMessage);
    }

    @DeleteMapping("/{messageId}")
    public ResponseEntity<Void> deleteMessage(@PathVariable UUID messageId) {
        messageService.deleteMessage(messageId);
        return ResponseEntity.noContent().build();
    }
}
