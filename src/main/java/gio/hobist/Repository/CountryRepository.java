package gio.hobist.Repository;

import gio.hobist.Entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Integer> {

    List<Country> findByName(String name);
}
