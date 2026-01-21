package gio.hobist.Service;

import gio.hobist.Dto.FriendshipDto;
import gio.hobist.Dto.MessageDto;
import gio.hobist.Entity.Message;
import gio.hobist.Repository.FriendshipRepository;
import gio.hobist.Repository.MessageRepository;
import gio.hobist.Repository.UserRepository;
import gio.hobist.utils.MessageEncryption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final MessageRepository messageRepository;
    private final FriendshipRepository friendshipRepository;
    private final UserRepository userRepository;

    private final MessageEncryption messageEncryption = new MessageEncryption();

   public FriendshipDto getFriendshipId(UUID friend1Id, UUID friend2Id) {
       var friendship=friendshipRepository.findByUser1IdAndUser2Id(friend1Id,friend2Id);

      return new FriendshipDto(friendship.getId(),
              friendship.getUser1().getId(),
              friendship.getUser2().getId(),
              friendship.getStatus(),
              friendship.getDateOfBefriending()
              );
   }


    public List<MessageDto> getAllMessages(UUID friendshipId) {
        var messages = messageRepository.findByFriendshipId(friendshipId);

        if (messages == null) {
            return null;
        }

        return messages.stream().map(message -> {
                try {
                    return new MessageDto(
                    message.getId(),
                            message.getFriendship().getId(),
                            message.getUser().getId(),
                            MessageEncryption.decrypt(message.getMessage().getBytes(StandardCharsets.UTF_8)),
                            message.getFile_name(),
                            message.getFile(),
                            message.getTimeSent().toLocalDateTime().toLocalTime().toString().substring(0, 5) //M.G: returns just hour and minutes
                    );
                }
                catch(InvalidKeyException e){
                    throw new RuntimeException(e);
                }
                catch(IllegalBlockSizeException e){
                    throw new RuntimeException(e);
                }
                catch (BadPaddingException e){
                    throw new RuntimeException(e);
                }
                }).toList();
    }

    public List<MessageDto> getLatestMessages(UUID friendshipId) {
        var messages = messageRepository.findTop36ByFriendshipIdOrderByTimeSentDesc(friendshipId);

        if (messages == null) {
            return null;
        }


            return messages.stream().map(message -> {
                try {
                    return new MessageDto(
                            message.getId(),
                            message.getFriendship().getId(),
                            message.getUser().getId(),
                            MessageEncryption.decrypt(message.getMessage().getBytes(StandardCharsets.UTF_8)),
                            message.getFile_name(),
                            message.getFile(),
                            message.getTimeSent().toLocalDateTime().toLocalTime().toString().substring(0, 5) //M.G: returns just hour and minutes
                    );
                } catch (InvalidKeyException e) {
                    throw new RuntimeException(e);
                } catch (IllegalBlockSizeException e) {
                    throw new RuntimeException(e);
                } catch (BadPaddingException e) {
                    throw new RuntimeException(e);
                }
            }).toList().reversed();
    }

    public void createMessage(MessageDto messageDto) {
       var friendship=friendshipRepository.findById(messageDto.getFriendshipId());
       var user=userRepository.findById(messageDto.getUserId());

       try {
           var encryptedMessage= MessageEncryption.encrypt(messageDto.getMessage());
           messageDto.setMessage(new String(encryptedMessage));
       }
       catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e){
           System.out.println(e.getMessage());
       }

        var message = new Message();
        message.setFriendship(friendship.get());
        message.setUser(user.get());
        message.setMessage(messageDto.getMessage());
//        message.setFile_name(messageDto.getFileName());M.G: I will work on it later

        messageRepository.save(message);

    }

    public void deleteMessage(UUID messageId) {
        messageRepository.deleteById(messageId);
    }
}
