package gio.hobist.Repository;

import gio.hobist.Entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByIdReceiverOrderByCreatedAtDesc(Long idReceiver);
}