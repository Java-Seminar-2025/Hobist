package gio.hobist.Controller;

import gio.hobist.Dto.AutenticationDto;
import gio.hobist.Service.AutenticationService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserSearchController {

    @GetMapping("/searchPage")
    public String searchPage() {
        return "searchPage";
    }

    @GetMapping("/searchPage/users")
    public String searchUsers(
            @RequestParam String q,
            Model model
    ) {
        List<User> users = userService.searchByName(q);
        model.addAttribute("users", users);
        return "common/search-results";
    }
}
