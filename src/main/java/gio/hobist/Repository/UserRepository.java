package gio.hobist.Repository;

import gio.hobist.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("Select u from User u where u.e_mail = :e_mail ")
    User getByEmail(@Param("e_mail")String e_mail);
}
