package lulunpengpeng.de.marketingcockpit.events;

import lombok.Value;
import lulunpengpeng.de.marketingcockpit.events.shared.EventType;
import lulunpengpeng.de.marketingcockpit.events.shared.Location;

import java.io.Serializable;
import java.time.LocalDate;

@Value
public class MailingListUpdatedEvent implements Serializable, MailingListEvent {
    private String mailingListId;
    private EventType eventType;
    private Location location;
    private LocalDate updatedTime;
}

