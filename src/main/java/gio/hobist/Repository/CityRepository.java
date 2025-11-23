package gio.hobist.Repository;

import gio.hobist.Entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CityRepository extends JpaRepository<City, Integer> {

    List<City> findByName(String name);

    List<City> findByCountryId(Integer countryId);
}
