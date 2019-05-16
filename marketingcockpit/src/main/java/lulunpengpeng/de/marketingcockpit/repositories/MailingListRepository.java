package lulunpengpeng.de.marketingcockpit.repositories;

import lulunpengpeng.de.marketingcockpit.events.shared.MailingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "mailinglists")
public interface MailingListRepository extends JpaRepository<MailingList, String> {
}
