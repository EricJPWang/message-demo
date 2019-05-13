package lulunpengpeng.de.contact.controllers;

import lombok.extern.log4j.Log4j2;
import lulunpengpeng.de.contact.domain.Contact;
import lulunpengpeng.de.contact.dtos.ContactDTO;
import lulunpengpeng.de.contact.events.ContactRemovedEvent;
import lulunpengpeng.de.contact.events.ContactUpdatedEvent;
import lulunpengpeng.de.contact.messaging.ContactChannels;
import lulunpengpeng.de.contact.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

import static java.time.LocalDate.now;

@RestController
@Log4j2
class ContactController {

    private ContactRepository contactRepository;
    private ContactChannels channels;

    @Autowired
    ContactController(ContactRepository contactRepository, ContactChannels channels) {
        this.contactRepository = contactRepository;
        this.channels = channels;
    }

    @PutMapping("contact")
    @ResponseStatus(HttpStatus.OK)
    void updateContact(@RequestBody ContactDTO contact) {
        Contact contactEntity = contactRepository.findById(contact.getContactId()).orElseThrow(EntityNotFoundException::new);
        contactEntity.setCompany(contact.getCompany());
        contactEntity.setEmail(contact.getEmail());
        contactEntity.setFirstName(contact.getFirstName());
        contactEntity.setLastName(contact.getLastName());
        contactRepository.save(contactEntity);
        channels.contactUpdatedOut().send(MessageBuilder.withPayload(
                new ContactUpdatedEvent(contactEntity.getId().toString(), contactEntity.hasWarning(), now())).build());
    }

    @DeleteMapping("contact/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteContact(@PathVariable String contactId) {
        Contact contactEntity = contactRepository.findById(contactId).orElseThrow(EntityNotFoundException::new);
        contactRepository.delete(contactEntity);
        channels.contactRemovedOut().send(MessageBuilder.withPayload(new ContactRemovedEvent(contactId, now())).build());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    void exceptionHandler() {

    }
}
