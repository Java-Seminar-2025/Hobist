package gio.hobist.Entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class HobbyUserId implements Serializable {
    private UUID uid;
    private UUID hobbyId;

    public HobbyUserId() {}

    public HobbyUserId(UUID uid, UUID hobbyId) {
        this.uid = uid;
        this.hobbyId = hobbyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HobbyUserId that = (HobbyUserId) o;
        return Objects.equals(uid, that.uid) && Objects.equals(hobbyId, that.hobbyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, hobbyId);
    }
}