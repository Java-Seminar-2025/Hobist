package gio.hobist.Repository;

import gio.hobist.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.UUID;
import java.util.List;
import java.lang.Integer;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);

    User findByid(UUID Id);

    List<User> findByCountryId(Integer countryId);

    List<User> findByCityId(Integer cityId);

    List<User> findByNameAndSurname(String name, String surname);
    
    List<User> findByNameContainingIgnoreCase(String name);
    
    @Query("SELECT u FROM User u JOIN HobbyUser hu ON u.id = hu.uid WHERE hu.hobbyId = :hobbyId")
    List<User> findByHobbyId(@Param("hobbyId") UUID hobbyId);
}
