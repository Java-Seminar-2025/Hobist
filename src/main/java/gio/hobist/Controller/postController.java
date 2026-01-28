package gio.hobist.Controller;

import gio.hobist.Dto.ContentLikeDto;
import gio.hobist.Entity.ContentLike;
import gio.hobist.Service.PostService;
import gio.hobist.Service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class postController {

    private final UserService userService;
    private final PostService postService;

    @GetMapping("/{postId}")
    public String post(@PathVariable UUID postId, Model model, HttpSession session){

       var userId=(UUID)session.getAttribute("userId");
       model.addAttribute("user",
               userService.getUser(userId));

       var post=postService.getPost(postId);
       model.addAttribute("post",post);

        return "common/post.html";
    }

    @PostMapping("/{postId}/like")
    public String postLike(@PathVariable UUID postId, HttpSession session){

        var userId=(UUID)session.getAttribute("userId");

        var contentLikeDto=new ContentLikeDto(
               null,
               postId,
               null,
               userId
        );

       postService.createLike(contentLikeDto);

        return "redirect:/post/" + postId;
    }
}
