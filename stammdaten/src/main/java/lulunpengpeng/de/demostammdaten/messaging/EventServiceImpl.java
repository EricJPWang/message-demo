package lulunpengpeng.de.demostammdaten.messaging;

import lombok.extern.log4j.Log4j2;
import lulunpengpeng.de.demostammdaten.events.MailinglistEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.springframework.messaging.support.MessageBuilder.withPayload;

@Component
@Log4j2
public class EventServiceImpl implements EventService {

    private StammdatenChannels stammdatenChannels;

    @Autowired
    public EventServiceImpl(StammdatenChannels stammdatenChannels) {
        this.stammdatenChannels = stammdatenChannels;
    }

    @Override
    public void sentMailinglistUpdatedEvent(MailinglistEvent event) {
        this.stammdatenChannels.mailingListUpdatedOut().send(withPayload(event).build());
        logEvent(event);
    }


    @Override
    public void sendMailinglistRemovedEvent(MailinglistEvent event) {
        this.stammdatenChannels.mailingListRemovedOut().send(withPayload(event).build());
        logEvent(event);
    }

    @Override
    public void sendMailinglistCreatedEvent(MailinglistEvent event) {

    }


    private void logEvent(MailinglistEvent event) {
        log.info("sent event {}", event);
    }
}
