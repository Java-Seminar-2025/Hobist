package gio.hobist.Entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class HobbyUserId implements Serializable {

    private UUID userId;

    private UUID hobbyId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HobbyUserId that = (HobbyUserId) o;
        return Objects.equals(userId, that.userId)
                && Objects.equals(hobbyId, that.hobbyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId,hobbyId);
    }
}
