package lulunpengpeng.de.marketingcockpit.repositories;

import lulunpengpeng.de.marketingcockpit.domain.shared.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "contacts")
public interface ContactRepository extends JpaRepository<Contact, String> {
}
