package lulunpengpeng.de.contact.controllers;

import lombok.extern.log4j.Log4j2;
import lulunpengpeng.de.contact.domain.Contact;
import lulunpengpeng.de.contact.dtos.ContactDTO;
import lulunpengpeng.de.contact.events.ContactRemovedEvent;
import lulunpengpeng.de.contact.events.ContactUpdatedEvent;
import lulunpengpeng.de.contact.messaging.ContactChannels;
import lulunpengpeng.de.contact.messaging.ContactMessagingService;
import lulunpengpeng.de.contact.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.time.LocalDate.now;
import static java.time.chrono.ChronoLocalDate.from;
import static java.util.UUID.fromString;
import static org.springframework.messaging.support.MessageBuilder.withPayload;

@RestController
@Log4j2
public class ContactController {

    private ContactRepository contactRepository;
    private ContactMessagingService contactMessagingService;


    @Autowired
    ContactController(ContactRepository contactRepository, ContactMessagingService contactMessagingService) {
        this.contactRepository = contactRepository;
        this.contactMessagingService = contactMessagingService;
    }

    @GetMapping("contacts")
    List<ContactDTO> getContacts() {
        return contactRepository.findAll().stream().map(ContactDTO::fromContact).collect(Collectors.toList());
    }


    @GetMapping("contact/{contactId}")
    ContactDTO getContact(@PathVariable  String contactId) {
        return contactRepository.findById(fromString(contactId)).map(ContactDTO::fromContact).orElseThrow(
                EntityNotFoundException::new
        );
    }


    @PutMapping("contact")
    @ResponseStatus(HttpStatus.OK)
    void updateContact(@RequestBody ContactDTO contact) {
        Contact contactEntity = findContact(contact.getContactId());
        contact.toContact(contactEntity);
        contactRepository.save(contactEntity);

        contactMessagingService.sendContactUpdatedEvent(new ContactUpdatedEvent(contactEntity.getId().toString(),
                contactEntity.hasWarning(), now()));

    }


    @DeleteMapping("contact/{contactId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteContact(@PathVariable String contactId) {
        Contact contactEntity = findContact(contactId);
        contactRepository.delete(contactEntity);
        contactMessagingService.sendContactRemovedEvent(new ContactRemovedEvent(contactId, now()));
    }

    private Contact findContact(String contactId) {
        return contactRepository.findById(fromString(contactId)).orElseThrow(EntityNotFoundException::new);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    void exceptionHandler() {

    }
}
