package gio.hobist.Controller;

import gio.hobist.Dto.PostDto;
import gio.hobist.Entity.Post;
import gio.hobist.Service.HomePageService;
import gio.hobist.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.UUID;

@Controller
public class HomePageController {

    @Autowired
    private final HomePageService homePageService;
    private final UserService userService;

    public HomePageController(HomePageService homePageService, UserService userService) {
        this.homePageService = homePageService;
        this.userService = userService;
    }


// M.G: unfinished

    @GetMapping(path="/home")
    public String home(Model model, HttpSession session){

        var userId=(UUID) session.getAttribute("userId");
        List<PostDto> feedPosts = homePageService.findAllPosts(userId);
        model.addAttribute("feed", feedPosts);

        var currentUser = userService.getCurrentAutenthicatedUser(userId);
        model.addAttribute("user", currentUser);

        return "homePage.html";
    }

}
