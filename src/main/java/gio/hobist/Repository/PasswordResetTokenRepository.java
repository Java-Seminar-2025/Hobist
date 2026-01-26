package gio.hobist.Repository;

import gio.hobist.Entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, UUID> {
    Optional<PasswordResetToken> findTopByTokenHashOrderByExpiresAtDesc(String tokenHash);
}
