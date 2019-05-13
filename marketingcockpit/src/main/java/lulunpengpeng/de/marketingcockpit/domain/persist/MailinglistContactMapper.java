package lulunpengpeng.de.marketingcockpit.domain.persist;

import lulunpengpeng.de.marketingcockpit.domain.shared.Contact;
import lulunpengpeng.de.marketingcockpit.domain.shared.MailingList;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "mailinglist-contact")
public class MailinglistContactMapper {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn
    private MailingList mailingList;

    @ManyToOne
    @JoinColumn
    private Contact contact;
}
