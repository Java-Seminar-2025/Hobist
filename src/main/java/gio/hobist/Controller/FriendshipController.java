package gio.hobist.Controller;

import gio.hobist.Service.ChatService;
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

    @GetMapping("/add/{friendId}")
    public String addFriend(HttpSession session, @PathVariable UUID friendId) {
        var userId = (UUID) session.getAttribute("userId");
        
        if (userId == null) {
            return "redirect:/";
        }

        // Kreiranje prijateljstva (ili slanje zahteva)
        try {
            chatService.createFriendship(userId, friendId);
            return "redirect:/searchPage?success=friend_added";
        } catch (Exception e) {
            return "redirect:/searchPage?error=friend_request_failed";
        }
    }
}