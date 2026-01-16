package gio.hobist.Controller;

import gio.hobist.Dto.AutenticationDto;
import gio.hobist.Dto.UserDto;
import gio.hobist.Service.AutenticationService;
import gio.hobist.Service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserSearchController {

    @Autowired
    private UserService userService;

    @GetMapping("/searchPage")
    public String searchPage() {
        return "searchPage";
    }

    @GetMapping("/searchPage/users")
    public String searchUsers(
            @RequestParam String name,
            @RequestParam String surname,
            Model model
    ) {
        List<UserDto> users = userService.searchByName(name,surname);
        model.addAttribute("users", users);
        return "common/search-results";
    }
}
