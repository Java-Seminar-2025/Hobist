package gio.hobist.Service;

import gio.hobist.Dto.MessageDto;
import gio.hobist.Entity.Friendship;
import gio.hobist.Repository.FriendshipRepository;
import gio.hobist.Repository.MessageRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Setter
@Getter
public class ChatService {

    @Autowired
    private final MessageRepository messageRepository;
    private final FriendshipRepository friendshipRepository;

   public Friendship getFriendshipId(UUID friend1Id, UUID friend2Id) {// vraca entitet nevalja to!!

      return  friendshipRepository.findByUser1IdAndUser2Id(friend1Id,friend2Id);
   }


    public List<MessageDto> getMessages(UUID friendshipId) {
        var messages = messageRepository.findByFriendshipId(friendshipId);

        if (messages == null) {
            return null;
        }

        return messages.stream().map(message -> new MessageDto(
                message.getId(),
                message.getFriendship().getId(),
                message.getUser().getId(),
                message.getMessage(),
                message.getFile_name(),
                message.getFile(),
                message.getTimeSent()
        )).toList();
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
