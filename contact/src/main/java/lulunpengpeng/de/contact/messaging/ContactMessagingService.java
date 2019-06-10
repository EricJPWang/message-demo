package lulunpengpeng.de.contact.messaging;

import lulunpengpeng.de.contact.events.ContactEvent;

public interface ContactMessagingService {
    void sendContactUpdatedEvent(ContactEvent event);

    void sendContactRemovedEvent(ContactEvent event);
}
