package gio.hobist.Controller;

import gio.hobist.Dto.UserDto;
import gio.hobist.Service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class UserSearchController {

    @Autowired
    private UserService userService;

    @GetMapping("/searchPage")
    public String searchPage(Model model, HttpSession session) {
        var userId = (UUID) session.getAttribute("userId");
        if (userId != null) {
            var currentUser = userService.getUser(userId);
            model.addAttribute("user", currentUser);
        }
        return "common/searchPage";
    }

    @GetMapping("/search/users")
    public String searchUsers(@RequestParam String q, Model model) {
        List<UserDto> users = userService.searchByQuery(q);
        model.addAttribute("users", users);
        return "common/search-results";
    }
}
