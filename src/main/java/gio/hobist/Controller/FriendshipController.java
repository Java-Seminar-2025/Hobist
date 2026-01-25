package gio.hobist.Controller;

import gio.hobist.Service.ChatService;
import gio.hobist.Service.NotificationService;
import gio.hobist.Service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/friendship")
@RequiredArgsConstructor
public class FriendshipController {

    private final ChatService chatService;
    private final NotificationService notificationService;
    private final UserService userService;

    @GetMapping("/add/{friendId}")
    public String addFriend(HttpSession session, @PathVariable UUID friendId) {
        var userId = (UUID) session.getAttribute("userId");

        // Creating friendship (or sending request)
        try {
            chatService.createFriendship(userId, friendId);
            
            // Send friend request notification
            var sender = userService.getUser(userId);
            notificationService.sendFriendRequestNotification(
                Long.valueOf(userId.toString().hashCode()),
                Long.valueOf(friendId.toString().hashCode()),
                sender.getName() + " " + sender.getSurname()
            );
            
            return "redirect:/searchPage?success=friend_added";
        } catch (Exception e) {
            return "redirect:/searchPage?error=friend_request_failed";
        }
    }
}