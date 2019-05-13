package lulunpengpeng.de.marketingcockpit.repositories;

import lulunpengpeng.de.marketingcockpit.domain.shared.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, String> {
}
