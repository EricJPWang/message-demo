package lulunpengpeng.de.contact.repositories;

import lulunpengpeng.de.contact.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

public interface ContactRepository extends JpaRepository<Contact, UUID> {
}
