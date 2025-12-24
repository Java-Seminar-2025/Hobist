package gio.hobist.Repository;

import gio.hobist.Entity.ContentLike;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface ContentLikeRepository extends JpaRepository<ContentLike, UUID> {

    List<ContentLike> findByPostId(UUID postId);

    List<ContentLike> findByCommentId(UUID commentId);

    List<ContentLike> findByUserId(UUID userId);
    
    ContentLike findByPostIdAndUserId(UUID postId, UUID userId);
    
    ContentLike findByCommentIdAndUserId(UUID commentId, UUID userId);
    
    int countByPostId(UUID postId);
    
    int countByCommentId(UUID commentId);
}
