package lulunpengpeng.de.demostammdaten.controllers;

import lulunpengpeng.de.demostammdaten.domain.EventType;
import lulunpengpeng.de.demostammdaten.domain.Location;
import lulunpengpeng.de.demostammdaten.domain.MailingList;
import lulunpengpeng.de.demostammdaten.dtos.EventTypeDTO;
import lulunpengpeng.de.demostammdaten.dtos.LocationDTO;
import lulunpengpeng.de.demostammdaten.dtos.MailingListDTO;
import lulunpengpeng.de.demostammdaten.events.MailingListCreatedEvent;
import lulunpengpeng.de.demostammdaten.events.MailingListDeletedEvent;
import lulunpengpeng.de.demostammdaten.events.MailingListUpdatedEvent;
import lulunpengpeng.de.demostammdaten.messaging.StammdatenChannels;
import lulunpengpeng.de.demostammdaten.repositories.EventTypeRepository;
import lulunpengpeng.de.demostammdaten.repositories.LocationRepository;
import lulunpengpeng.de.demostammdaten.repositories.MailingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import static java.time.LocalDate.now;
import static java.util.UUID.fromString;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.messaging.support.MessageBuilder.withPayload;

@RestController
public class MailingListController {

    private MailingListRepository mailingListRepository;
    private StammdatenChannels stammdatenChannels;
    private LocationRepository locationRepository;
    private EventTypeRepository eventTypeRepository;

    @Autowired
    MailingListController(MailingListRepository mailingListRepository,
                          LocationRepository locationRepository,
                          EventTypeRepository eventTypeRepository,
                          StammdatenChannels stammdatenChannels) {
        this.mailingListRepository = mailingListRepository;
        this.stammdatenChannels = stammdatenChannels;
        this.locationRepository = locationRepository;
        this.eventTypeRepository = eventTypeRepository;
    }

    @GetMapping("mailinglists")
    List<MailingListDTO> getMailingLists() {
        return this.mailingListRepository.findAll().stream().map(MailingListDTO::from).collect(toList());
    }

    @GetMapping("mailinglist/{mailingListId}")
    MailingListDTO getMailingList(@PathVariable String mailingListId) {
        return this.mailingListRepository.findById(fromString(mailingListId)).map(MailingListDTO::from).orElseThrow(
                EntityNotFoundException::new
        );
    }

    @PutMapping(path = "mailinglist")
    @ResponseStatus(OK)
    void updateMailingList(@RequestBody MailingListDTO mailingList) {
        MailingList mailingListEntity = mailingListRepository.findById(fromString(mailingList.getMailingListId()))
                .orElseThrow(EntityNotFoundException::new);
        Location location = locationRepository.findLocationByName(mailingList.getLocation())
                .orElseGet(() -> this.newLocation(mailingList.getLocation()));
        EventType eventType = eventTypeRepository.findEventTypeByName(mailingList.getEventType())
                .orElseGet(() -> this.newEventType(mailingList.getEventType()));

        mailingListEntity.setLocation(location);
        mailingListEntity.setEventType(eventType);
        mailingListRepository.save(mailingListEntity);

        MailingListUpdatedEvent event = new MailingListUpdatedEvent(
                mailingList.getMailingListId(),
                LocationDTO.fromLocation(location),
                EventTypeDTO.fromEventType(eventType),
                now());
        this.stammdatenChannels.mailingListUpdatedOut().send(withPayload(event).build());
    }

    @DeleteMapping(path = "mailinglist/{mailinglistId}")
    @ResponseStatus(NO_CONTENT)
    void deleteMailingList(@PathVariable String mailinglistId) {
        MailingList mailingListEntity = mailingListRepository.findById(fromString(mailinglistId))
                .orElseThrow(EntityNotFoundException::new);
        mailingListRepository.delete(mailingListEntity);
        MailingListDeletedEvent event = new MailingListDeletedEvent(mailinglistId, now());
        this.stammdatenChannels.mailingListRemovedOut().send(withPayload(event).build());
    }

    @ExceptionHandler
    @ResponseStatus(NOT_FOUND)
    void exceptionHandler(EntityNotFoundException exception) {

    }

    private Location newLocation(String locationName) {
        return locationRepository.save(new Location(locationName));
    }

    private EventType newEventType(String eventTypeName) {
        return eventTypeRepository.save(new EventType(eventTypeName));
    }
}
