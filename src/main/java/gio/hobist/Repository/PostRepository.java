package gio.hobist.Repository;

import gio.hobist.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {

    List<Post> findAllByIdUser(UUID Id);
    
    List<Post> findAllByOrderByCreatedAtDesc();
    
    @Query("SELECT p FROM Post p WHERE p.idUser IN :userIds ORDER BY p.createdAt DESC")
    List<Post> findPostsByUserIds(@Param("userIds") List<UUID> userIds);
}
