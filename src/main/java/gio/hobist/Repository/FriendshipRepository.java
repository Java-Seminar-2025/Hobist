package gio.hobist.Repository;

import gio.hobist.Entity.Friendship;
import gio.hobist.Enum.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.UUID;

public interface FriendshipRepository extends JpaRepository<Friendship, UUID> {

    List<Friendship> findByUser1IdOrUser2Id(UUID user1, UUID user2);
    
    @Query("SELECT f FROM Friendship f WHERE (f.user1.id = :userId OR f.user2.id = :userId) AND f.status = :status")
    List<Friendship> findByUserIdAndStatus(@Param("userId") UUID userId, @Param("status") Status status);
    
    @Query("SELECT f FROM Friendship f WHERE f.user2.id = :userId AND f.status = 'PENDING'")
    List<Friendship> findPendingRequestsForUser(@Param("userId") UUID userId);
    
    @Query("SELECT f FROM Friendship f WHERE ((f.user1.id = :user1Id AND f.user2.id = :user2Id) OR (f.user1.id = :user2Id AND f.user2.id = :user1Id))")
    Friendship findByUsers(@Param("user1Id") UUID user1Id, @Param("user2Id") UUID user2Id);
}
