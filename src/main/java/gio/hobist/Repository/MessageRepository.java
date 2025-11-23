package gio.hobist.Repository;

import gio.hobist.Entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {

    List<Message> findByIdFriendship(UUID friendshipId);

    List<Message> findByIdUser(UUID userId);
}
