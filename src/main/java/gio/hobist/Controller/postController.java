package gio.hobist.Controller;

import gio.hobist.Dto.CommentDto;
import gio.hobist.Dto.ContentLikeDto;
import gio.hobist.Entity.ContentLike;
import gio.hobist.Service.CommentService;
import gio.hobist.Service.PostService;
import gio.hobist.Service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class postController {

    private final UserService userService;
    private final PostService postService;
    private final CommentService commentService;

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
    @PostMapping("/{postId}/submitComment")
    public String submitComment(@PathVariable UUID postId,HttpSession session,@ModelAttribute(name = "comment") String comment) {

        var userId=(UUID)session.getAttribute("userId");

       var commentDto=new CommentDto(
               null,
               postId,
               userId,
               null,
               comment,
               null
       );

      commentService.createComment(commentDto);

        return "redirect:/post/"+ postId;
    }
}
