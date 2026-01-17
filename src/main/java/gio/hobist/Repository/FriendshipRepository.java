package gio.hobist.Repository;

import gio.hobist.Entity.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface FriendshipRepository extends JpaRepository<Friendship, UUID> {

    Friendship findByUser1IdAndUser2Id(UUID user1, UUID user2);
}
