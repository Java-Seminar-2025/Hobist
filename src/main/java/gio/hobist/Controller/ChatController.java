package gio.hobist.Controller;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;
    private final UserService userService;

    @GetMapping("/{userId}")
    public String openChat(@PathVariable Long userId, Model model) {

        model.addAttribute("activeUser",
                userService.getById(userId));

        model.addAttribute("messages",
                chatService.getMessagesWith(userId));

        model.addAttribute("myId",
                userService.getCurrentUserId());

        return "chat";
    }
}
