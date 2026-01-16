package gio.hobist.Controller;

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

//    private final ChatService chatService;
    @Autowired
    private final UserService userService;


    @GetMapping("/{FriendId}")
    public String openChat(HttpSession session, @PathVariable Long friendId, Model model) {

//
//        model.addAttribute("activeUser",
//                userService.getById(friendId));
//
//        model.addAttribute("messages",
//                chatService.getMessagesWith(friendId));

        var userId=(UUID)session.getAttribute("userId");
        model.addAttribute("userId",
                userService.getCurrentAutenthicatedUser(userId));

        return "chat";
    }
}
