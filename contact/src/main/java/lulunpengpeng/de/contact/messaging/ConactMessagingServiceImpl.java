package lulunpengpeng.de.contact.messaging;

import lombok.extern.log4j.Log4j2;
import lulunpengpeng.de.contact.events.ContactEvent;
import lulunpengpeng.de.contact.events.ContactRemovedEvent;
import lulunpengpeng.de.contact.events.ContactUpdatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.time.LocalDate.now;
import static org.springframework.messaging.support.MessageBuilder.withPayload;

@Component
@Log4j2
class ConactMessagingServiceImpl implements ContactMessagingService {

    @Autowired
    private ContactChannels channels;

    @Override
    public void sendContactUpdatedEvent(ContactEvent event) {
        log.info("updatedEvent" + event);
        channels.contactUpdatedOut().send(withPayload(event).build());
    }

    @Override
    public void sendContactRemovedEvent(ContactEvent event) {
        channels.contactRemovedOut().send(withPayload(event).build());
    }
}
