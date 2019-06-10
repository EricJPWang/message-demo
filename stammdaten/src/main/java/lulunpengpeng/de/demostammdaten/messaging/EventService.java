package lulunpengpeng.de.demostammdaten.messaging;

import lulunpengpeng.de.demostammdaten.events.MailinglistEvent;

public interface EventService {
    void sentMailinglistUpdatedEvent(MailinglistEvent event);

    void sendMailinglistRemovedEvent(MailinglistEvent event);

    void sendMailinglistCreatedEvent(MailinglistEvent event);
}
