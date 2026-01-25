package gio.hobist.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long idSender;
    private Long idReceiver;
    private String subject;
    private String description;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    public Notification(Long idSender, Long idReceiver, String subject, String description) {
        this.idSender = idSender;
        this.idReceiver = idReceiver;
        this.subject = subject;
        this.description = description;
        this.createdAt = LocalDateTime.now();
    }
}