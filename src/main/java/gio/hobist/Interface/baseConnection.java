package gio.hobist.Interface;

import gio.hobist.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface baseConnection extends JpaRepository<User,Long> {

    @Query("Select u from User u where u.e_mail = :e_mail ")
    User getByEmail(@Param("e_mail")String e_mail);
}
