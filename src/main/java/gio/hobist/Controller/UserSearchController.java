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
    public String searchUsers(
            @RequestParam(required = false) String q,
            @RequestParam(defaultValue = "all") String mode,
            Model model,
            HttpSession session) {

        UUID userId = (UUID) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/login";
        }

        model.addAttribute("user", userService.getUser(userId));

        if (q == null || q.trim().isEmpty()) {
            return "common/searchPage";
        }
        if (q != null && !q.trim().isEmpty()) {
            if ("sharedHobby".equals(mode)){
                try {
                    List<UserDto> users = userService.searchByQueryWithSharedHobby(q, userId)
                            .stream()
                            .filter(u -> !u.getId().equals(userId))
                            .toList();
                    model.addAttribute("users", users);
                    if (users.isEmpty()) {
                        model.addAttribute("noResults", "No users found for: " + q);
                    }
                } catch (Exception e) {
                    model.addAttribute("errorMessage", "Search failed. Please try again.");
                }
            }
            else{
                try {
                    List<UserDto> users = userService.searchByQuery(q)
                            .stream()
                            .filter(u -> !u.getId().equals(userId))
                            .toList();
                    model.addAttribute("users", users);
                    if (users.isEmpty()) {
                        model.addAttribute("noResults", "No users found for: " + q);
                    }
                } catch (Exception e) {
                    model.addAttribute("errorMessage", "Search failed. Please try again.");
                }
            }
        }
        return "common/searchPage";
    }
}
