package gio.hobist.Controller;

import gio.hobist.Service.ChatService;
import gio.hobist.Service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    @Autowired
    private final ChatService chatService;
    @Autowired
    private final UserService userService;


    @GetMapping("/{friendId}")
    public String openChat(HttpSession session, @PathVariable UUID friendId, Model model) {


        model.addAttribute("activeUser",
                userService.getUser(friendId));

        var userId=(UUID)session.getAttribute("userId");
        model.addAttribute("user",
                userService.getUser(userId));

        var friendship=chatService.getFriendshipId(userId,friendId);
        model.addAttribute("messages",
                chatService.getMessages(friendship.getId()));

        return "common/chat.html";
    }
}
