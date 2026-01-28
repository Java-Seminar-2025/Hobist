package gio.hobist.Service;

import gio.hobist.Dto.ContentLikeDto;
import gio.hobist.Dto.PostDto;
import gio.hobist.Entity.ContentLike;
import gio.hobist.Repository.CommentRepository;
import gio.hobist.Repository.ContentLikeRepository;
import gio.hobist.Repository.PostRepository;
import gio.hobist.Repository.UserRepository;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ContentLikeRepository contentLikeRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public PostDto getPost(UUID postId){
       var post=postRepository.findByid(postId);

       var contentLike=contentLikeRepository.findByPostId(postId);

       var likeNumber=contentLike.size();

        return new PostDto(
               post.getId(),
               post.getIdUser(),
               post.getMessage(),
               post.getImage(),
               likeNumber,
               post.getCreatedAt()
       );
    }

    @Transactional
    public void createLike(ContentLikeDto contentLikeDto){

        var post=postRepository.findByid(contentLikeDto.getPostId());

        var user=userRepository.findById(contentLikeDto.getUserId()).get();

        var contentLike=new ContentLike(
                null,
                post,
                null,
                user
        );


        if(!isLiked(post.getId(),user.getId(),null)){
            contentLikeRepository.save(contentLike);
        }
        else {
            contentLikeRepository.deleteByPostIdAndUserIdAndCommentId(post.getId(),user.getId(),null);
        }
    }

    public boolean isLiked(UUID postId, UUID userId, UUID commentId){
        var isLiked=contentLikeRepository.findByPostIdAndUserIdAndCommentId(
                postId,
                userId,
                commentId);

        return !isLiked.isEmpty();//M.G: method is called isLiked so by default method should return true
    }

}
