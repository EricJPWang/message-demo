package lulunpengpeng.de.marketingcockpit.messaging;

import lombok.extern.log4j.Log4j2;
import lulunpengpeng.de.marketingcockpit.events.shared.Contact;
import lulunpengpeng.de.marketingcockpit.events.shared.MailingList;
import lulunpengpeng.de.marketingcockpit.events.*;
import lulunpengpeng.de.marketingcockpit.repositories.ContactRepository;
import lulunpengpeng.de.marketingcockpit.repositories.MailingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Log4j2
public class IncomingMessageListener {

    private MailingListRepository mailingListRepository;
    private ContactRepository contactRepository;

    @Autowired
    public IncomingMessageListener(
            MailingListRepository mailingListRepository,
            ContactRepository contactRepository) {
        this.mailingListRepository = mailingListRepository;
        this.contactRepository = contactRepository;
    }

    @StreamListener(MarketingCockpitChannels.MAILING_LIST_CREATED_IN)
    void receiveMailingListCreatedEvent(MailingListCreatedEvent event) {
        log.info("receiving mailinglist created event" + event);
        Optional<MailingList> mailingListOptional = mailingListRepository.findById(event.getMailingListId());
        if (mailingListOptional.isPresent()) {
            log.error(String.format("mailing with given id %s exists already", event.getMailingListId()));
            return;
        }
        saveMailingListFromEvent(event);
    }


    @StreamListener(MarketingCockpitChannels.MAILING_LIST_UPDATED_IN)
    void receiveMailingListUpatedEvent(MailingListUpdatedEvent event) {
        log.info("receiving mailinglist updated event" + event);
        Optional<MailingList> mailingListOptional = mailingListRepository.findById(event.getMailingListId());
        if (!mailingListOptional.isPresent()) {
            log.error(String.format("Cannot find mailing list with id %s for mailinglist removed event",
                    event.getMailingListId()));
            return;
        }
        saveMailingListFromEvent(event);
    }


    @StreamListener(MarketingCockpitChannels.MAILING_LIST_REMOVED_IN)
    void receiveMailingListRemovedEvent(MailingListRemovedEvent event) {
        log.info(("receiving mailinglist removed event" + event));
        Optional<MailingList> mailingListOptional = mailingListRepository.findById(event.getMailingListId());
        if (!mailingListOptional.isPresent()) {
            log.error(String.format("Cannot find mailing list with id %s for mailinglist removed event",
                    event.getMailingListId()));
            return;
        }
        mailingListRepository.delete(mailingListOptional.get());
    }


    @StreamListener(MarketingCockpitChannels.CONTACT_CREATED_IN)
    void receiveContactCreatedEvent(ContactCreatedEvent event) {
        log.info("receiving contact created event " + event);
        Optional<Contact> contactOptional = contactRepository.findById(event.getContactId());
        if (contactOptional.isPresent()) {
            log.error(String.format("contact with given id %s exists already", event.getContactId()));
            return;
        }
        saveContactFromEvent(event);
    }

    @StreamListener(MarketingCockpitChannels.CONTACT_REMOVED_IN)
    void receiveContactRemovedEvent(ContactRemovedEvent event) {
        log.info("receiving contact removed event");
        Optional<Contact> contactOptional = contactRepository.findById(event.getContactId());
        if (!contactOptional.isPresent()) {
            log.error(String.format("cannot find contact with given id ", event.getContactId()));
            return;
        }
        saveContactFromEvent(event);
    }

    @StreamListener(MarketingCockpitChannels.CONTACT_UPDATED_IN)
    void receiveContactUpdatedEvent(ContactUpdatedEvent event) {
        log.info("receiving contact updated event " + event);
        Optional<Contact> contactOptional = contactRepository.findById(event.getContactId());
        if (!contactOptional.isPresent()) {
            log.error(String.format("cannot find contact with given id ", event.getContactId()));
            return;
        }
        Contact contactEntity = contactOptional.get();
        contactEntity.setHasWarning(event.isHasWarning());
        contactRepository.save(contactEntity);
    }

    private void saveMailingListFromEvent(MailingListEvent event) {
        MailingList mailingList = MailingList.builder().mailingListId(event.getMailingListId())
                .location(event.getLocation())
                .eventType(event.getEventType())
                .build();
        mailingListRepository.save(mailingList);
    }

    private void saveContactFromEvent(ContactEvent event) {
        Contact contact = Contact.builder().contactId(event.getContactId()).hasWarning(event.isHasWarning()).build();
        contactRepository.save(contact);
    }
}
