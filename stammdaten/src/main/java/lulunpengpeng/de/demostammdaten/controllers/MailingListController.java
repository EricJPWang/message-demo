package lulunpengpeng.de.demostammdaten.controllers;

import lulunpengpeng.de.demostammdaten.domain.MailingList;
import lulunpengpeng.de.demostammdaten.dtos.MailingListDTO;
import lulunpengpeng.de.demostammdaten.events.MailingListCreatedEvent;
import lulunpengpeng.de.demostammdaten.events.MailingListDeletedEvent;
import lulunpengpeng.de.demostammdaten.events.MailingListUpdatedEvent;
import lulunpengpeng.de.demostammdaten.messaging.StammdatenChannels;
import lulunpengpeng.de.demostammdaten.repositories.MailingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

import static java.time.LocalDate.now;
import static java.util.UUID.fromString;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.messaging.support.MessageBuilder.withPayload;

@RestController
public class MailingListController {

    private MailingListRepository mailingListRepository;
    private StammdatenChannels stammdatenChannels;

    @Autowired
    MailingListController(MailingListRepository mailingListRepository, StammdatenChannels stammdatenChannels) {
        this.mailingListRepository = mailingListRepository;
        this.stammdatenChannels = stammdatenChannels;
    }

    @PutMapping(path = "mailinglist")
    @ResponseStatus(OK)
    void updateMailingList(@RequestBody MailingListDTO mailingList) {
        MailingList mailingListEntity = mailingListRepository.findById(fromString(mailingList.getMailingListId()))
                .orElseThrow(EntityNotFoundException::new);
        mailingListEntity.setLocation(mailingList.getLocation());
        mailingListEntity.setEventType(mailingList.getEventType());
        mailingListRepository.save(mailingListEntity);

        MailingListUpdatedEvent event = new MailingListUpdatedEvent(mailingList.getMailingListId(),
                mailingList.getLocation(), mailingList.getEventType(), now());
        this.stammdatenChannels.mailingListUpdatedOut().send(withPayload(event).build());
    }

    @DeleteMapping(path = "mailinglist/{mailinglistId}")
    @ResponseStatus(NO_CONTENT)
    void deleteMailingList(@PathVariable String mailingListId) {
        MailingList mailingListEntity = mailingListRepository.findById(fromString(mailingListId))
                .orElseThrow(EntityNotFoundException::new);
        mailingListRepository.delete(mailingListEntity);
        MailingListDeletedEvent event = new MailingListDeletedEvent(mailingListId, now());
        this.stammdatenChannels.mailingListRemovedOut().send(withPayload(event).build());
    }

    @PostMapping(path = "mailinglist")
    @ResponseStatus(CREATED)
    void createMailingList(@RequestBody MailingListDTO mailingList) {
        MailingList mailingListEntity = mailingListRepository.save(
                new MailingList(mailingList.getLocation(), mailingList.getEventType()));
        MailingListCreatedEvent event = new MailingListCreatedEvent(mailingListEntity.getId().toString(), now());
        this.stammdatenChannels.mailingListCreatedOut().send(withPayload(event).build());
    }

    @ExceptionHandler
    @ResponseStatus(NOT_FOUND)
    void exceptionHandler(EntityNotFoundException exception) {

    }
}
