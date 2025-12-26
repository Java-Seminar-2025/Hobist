package gio.hobist.Service;

import gio.hobist.Dto.MessageDto;
import gio.hobist.Entity.Message;
import gio.hobist.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.UUID;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public MessageDto getCurrentAutenthicatedMessage(UUID messageId) {
        var message = messageRepository.findById(messageId).orElse(null);

        if (message == null) {
            return null;
        }

        return new MessageDto(
            message.getId(),
            message.getFriendship().getId(),
            message.getUser().getId(),
            message.getMessage(),
            message.getFile_name(),
            message.getFile() != null ? Base64.getEncoder().encodeToString(message.getFile()) : null,
            message.getTimeSent()
        );
    }

    public MessageDto updateMessage(UUID messageId, MessageDto messageDto) {
        var message = messageRepository.findById(messageId).orElse(null);

        if (message == null) {
            return null;
        }

        message.setMessage(messageDto.getMessage());
        message.setFile_name(messageDto.getFileName());
        if (messageDto.getFileBase64() != null) {
            message.setFile(Base64.getDecoder().decode(messageDto.getFileBase64()));
        }

        messageRepository.save(message);

        return new MessageDto(
            message.getId(),
            message.getFriendship().getId(),
            message.getUser().getId(),
            message.getMessage(),
            message.getFile_name(),
            message.getFile() != null ? Base64.getEncoder().encodeToString(message.getFile()) : null,
            message.getTimeSent()
        );
    }

    public MessageDto createMessage(MessageDto messageDto) {
        var message = new Message();
        message.setMessage(messageDto.getMessage());
        message.setFile_name(messageDto.getFileName());
        if (messageDto.getFileBase64() != null) {
            message.setFile(Base64.getDecoder().decode(messageDto.getFileBase64()));
        }

        messageRepository.save(message);

        return new MessageDto(
            message.getId(),
            message.getFriendship().getId(),
            message.getUser().getId(),
            message.getMessage(),
            message.getFile_name(),
            message.getFile() != null ? Base64.getEncoder().encodeToString(message.getFile()) : null,
            message.getTimeSent()
        );
    }

    public void deleteMessage(UUID messageId) {
        messageRepository.deleteById(messageId);
    }
}