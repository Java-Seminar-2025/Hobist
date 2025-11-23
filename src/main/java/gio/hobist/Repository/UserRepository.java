package gio.hobist.Repository;

import gio.hobist.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.List;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEMail(String e_mail);

    List<User> findByIdCountry(Integer idCountry);

    List<User> findByIdCity(Integer idCity);

    List<User> findByNameAndSurname(String name, String surname);
}
