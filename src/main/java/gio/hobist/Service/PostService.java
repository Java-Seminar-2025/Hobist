package gio.hobist.Service;

import gio.hobist.Dto.PostDto;
import gio.hobist.Entity.Post;
import gio.hobist.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.UUID;

@Service
public class PostService {
    
    @Autowired
    private PostRepository postRepository;
    
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void deletePost(UUID postId) {
        postRepository.deleteById(postId);
    }

    public PostDto getCurrentAutenthicatedPost(UUID postId){
        var post = postRepository.findById(postId).orElse(null);
        
        if (post == null) {
            return null;
        }
        
        return new PostDto(
            post.getId(),
            post.getIdUser(),
            post.getMessage(),
            Base64.getEncoder().encodeToString(post.getImage()),
            post.getLikeNumber(),
            post.getCreatedAt()
        );
    }

    public PostDto createPost(PostDto postDto) {
        var post = new Post();
        post.setIdUser(postDto.getUserId());
        post.setMessage(postDto.getMessage());
        post.setImage(Base64.getDecoder().decode(postDto.getRawImage()));
        post.setLikeNumber(postDto.getLikeNumber());
        post.setCreatedAt(postDto.getCreatedAt());

        postRepository.save(post);

        return new PostDto(
            post.getId(),
            post.getIdUser(),
            post.getMessage(),
            Base64.getEncoder().encodeToString(post.getImage()),
            post.getLikeNumber(),
            post.getCreatedAt()
        );
    }

    public PostDto updatePost(UUID postId, PostDto postDto) {
        var post = postRepository.findById(postId).orElse(null);

        if (post == null) {
            return null;
        }

        post.setMessage(postDto.getMessage());
        post.setLikeNumber(postDto.getLikeNumber());

        postRepository.save(post);

        return new PostDto(
            post.getId(),
            post.getIdUser(),
            post.getMessage(),
            Base64.getEncoder().encodeToString(post.getImage()),
            post.getLikeNumber(),
            post.getCreatedAt()
        );
    }


}
