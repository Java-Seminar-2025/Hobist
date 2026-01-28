package gio.hobist.Service;

import gio.hobist.Dto.PostDto;
import gio.hobist.Repository.ContentLikeRepository;
import gio.hobist.Repository.PostRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HomePageService {

    private final PostRepository postRepository;
    private final ContentLikeRepository contentLikeRepository;

   public List<PostDto> findAllPosts(UUID userId){
       var postList=postRepository.findAllByIdUser(userId);

      return postList.stream().map( post ->{

          var contentLike=contentLikeRepository.findByPostId(post.getId());

          var likeNumber=contentLike.size();

          return new PostDto(
                  post.getId(),
               post.getIdUser(),
               post.getMessage(),
               post.getImage(),
               likeNumber,
               post.getCreatedAt()
          );

      }).toList();
   }


}
