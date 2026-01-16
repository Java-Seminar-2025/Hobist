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

    public MessageDto getMessage(UUID messageId) {
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
            message.getFile(),
            message.getTimeSent()
        );
    }



//    public MessageDto createMessage(MessageDto messageDto) {//M.G:??
//        var message = new Message();
//        message.setMessage(messageDto.getMessage());
//        message.setFile_name(messageDto.getFileName());
//
//        messageRepository.save(message);
//
//        return new MessageDto(
//            message.getId(),
//            message.getFriendship().getId(),
//            message.getUser().getId(),
//            message.getMessage(),
//            message.getFile_name(),
//            message.getFile(),
//            message.getTimeSent()
//        );
//    }

    public void deleteMessage(UUID messageId) {
        messageRepository.deleteById(messageId);
    }
}