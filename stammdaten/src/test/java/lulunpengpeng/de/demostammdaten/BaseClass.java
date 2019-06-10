package lulunpengpeng.de.demostammdaten;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import lulunpengpeng.de.demostammdaten.dtos.EventTypeDTO;
import lulunpengpeng.de.demostammdaten.dtos.LocationDTO;
import lulunpengpeng.de.demostammdaten.events.MailingListDeletedEvent;
import lulunpengpeng.de.demostammdaten.events.MailingListUpdatedEvent;
import lulunpengpeng.de.demostammdaten.events.MailinglistEvent;
import lulunpengpeng.de.demostammdaten.messaging.EventService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoStammdatenApplication.class)
@AutoConfigureMessageVerifier
public abstract class BaseClass {

    private static final String MAILINGLIST_ID = "mailinglist_id";
    private static final String LOCALTION_ID = "location_id";
    private static final String LOCATION_NAME = "location_name";
    private static final String EVENT_TYPE_ID = "eventType_id";
    private static final String EVENT_TYPE_NAME = "eventType_name";


    @Autowired
    private EventService eventService;
    private LocalDate updatedTime;

    public void triggerMailinglistUpdatedEvent() {
        LocationDTO locationDTO = LocationDTO.builder()
                .locationId(LOCALTION_ID).locationName(LOCATION_NAME).build();
        EventTypeDTO eventTypeDTO = EventTypeDTO.builder().eventTypeId(EVENT_TYPE_ID)
                .eventTypeName(EVENT_TYPE_NAME).build();
        MailingListUpdatedEvent mailingListUpdatedEvent = MailingListUpdatedEvent.builder()
                .mailingListId(MAILINGLIST_ID).eventType(eventTypeDTO)
                .location(locationDTO).updatedTime(updatedTime).build();
        this.eventService.sentMailinglistUpdatedEvent(mailingListUpdatedEvent);
    }

    public void triggerMailinglistRemovedEvent() {
        MailinglistEvent mailingListDeletedEvent = new MailingListDeletedEvent(
                MAILINGLIST_ID, updatedTime);
        this.eventService.sendMailinglistRemovedEvent(mailingListDeletedEvent);
    }

    @Before
    public void setUp() {
        RestAssuredMockMvc.standaloneSetup(this.eventService);
        updatedTime = LocalDate.of(1970, 1, 1);
    }
}
