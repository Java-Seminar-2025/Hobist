package gio.hobist.Service;

import gio.hobist.Entity.Notification;
import gio.hobist.Repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    
    private final NotificationRepository notificationRepository;
    
    public void sendLikeNotification(Long senderId, Long receiverId, String senderName, String postTitle) {
        var notification = new Notification(
            senderId, 
            receiverId, 
            "LIKE", 
            senderName + " liked your post: " + postTitle
        );
        notificationRepository.save(notification);
    }
    
    public void sendCommentNotification(Long senderId, Long receiverId, String senderName, String postTitle) {
        var notification = new Notification(
            senderId, 
            receiverId, 
            "COMMENT", 
            senderName + " commented on your post: " + postTitle
        );
        notificationRepository.save(notification);
    }
    
    public void sendFriendRequestNotification(Long senderId, Long receiverId, String senderName) {
        var notification = new Notification(
            senderId, 
            receiverId, 
            "FRIEND_REQUEST", 
            senderName + " sent you a friend request"
        );
        notificationRepository.save(notification);
    }
    
    public List<Notification> getUserNotifications(Long userId) {
        return notificationRepository.findByIdReceiverOrderByCreatedAtDesc(userId);
    }
}