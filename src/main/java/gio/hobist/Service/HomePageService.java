package gio.hobist.Service;

import gio.hobist.Dto.PostDto;
import gio.hobist.Repository.PostRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;
import java.util.UUID;

@Service
public class HomePageService {

    @Autowired
    private final PostRepository postRepository;

    public HomePageService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
    }

   public List<PostDto> findAllPosts(UUID userId){
       var postList=postRepository.findAllByIdUser(userId);

       return postList.stream().map(post -> new PostDto(
               post.getId(),
               post.getIdUser(),
               post.getMessage(),
               post.getImage(),
               post.getLikeNumber(),
               post.getCreatedAt()
       )).toList();
   }


}
