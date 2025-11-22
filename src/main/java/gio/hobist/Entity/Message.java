package gio.hobist.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_friendship")
    private Friendship friendship;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    private String message;

    @Column(name = "raw_data_name")
    private String rawDataName;

    @Column(name = "raw_data")
    private byte[] rawData;

    @Column(name = "time_sent")
    private java.time.OffsetDateTime timeSent;

    public Message() { super(); }
}
