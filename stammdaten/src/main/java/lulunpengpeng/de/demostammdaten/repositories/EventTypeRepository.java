package lulunpengpeng.de.demostammdaten.repositories;

import lulunpengpeng.de.demostammdaten.domain.EventType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EventTypeRepository extends JpaRepository<EventType, UUID> {
    Optional<EventType> findEventTypeByName(String name);
}
