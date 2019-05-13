package lulunpengpeng.de.marketingcockpit.repositories;

import lulunpengpeng.de.marketingcockpit.domain.shared.MailingList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MailingListRepository extends JpaRepository<MailingList, String> {
}
