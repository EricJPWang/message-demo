package lulunpengpeng.de.demostammdaten.repositories;

import lulunpengpeng.de.demostammdaten.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LocationRepository extends JpaRepository<Location, UUID> {
    Optional<Location> findLocationByName(String name);
}
