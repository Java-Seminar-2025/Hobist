package gio.hobist.Service;

import gio.hobist.Dto.PostDto;
import gio.hobist.Entity.Post;
import gio.hobist.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;


import java.util.List;
import java.util.UUID;

@Service
public class HomePageService {

    @Autowired
    private PostRepository postRepository;

    public HomePageService(PostRepository postRepository) {
        this.postRepository = postRepository;

    }

   public List<PostDto> findAllPosts(UUID userId){
       var postList=postRepository.findAllByIdUser(userId);

       return postList.stream().map(post -> new PostDto(
               post.getId(),
               post.getIdUser(),
               post.getMessage(),
               Base64.getEncoder().encodeToString(
                       ( post.getImage()==null ) ? new byte[0] : post.getImage()//quick fix for null error handling
                    ),
               post.getLikeNumber(),
               post.getCreatedAt()
       )).toList();



   }

}
