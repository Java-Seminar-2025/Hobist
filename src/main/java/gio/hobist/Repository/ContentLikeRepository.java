package gio.hobist.Repository;

import gio.hobist.Entity.ContentLike;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface ContentLikeRepository extends JpaRepository<ContentLike, UUID> {

    List<ContentLike> findByIdPost(UUID postId);

    List<ContentLike> findByIdComment(UUID commentId);

    List<ContentLike> findByIdUser(UUID userId);
}
