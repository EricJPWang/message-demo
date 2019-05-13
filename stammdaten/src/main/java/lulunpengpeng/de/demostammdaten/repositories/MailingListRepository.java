package lulunpengpeng.de.demostammdaten.repositories;

import lulunpengpeng.de.demostammdaten.domain.MailingList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface MailingListRepository extends JpaRepository<MailingList, UUID> {
}
