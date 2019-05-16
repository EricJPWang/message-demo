package lulunpengpeng.de.marketingcockpit.events;

import lulunpengpeng.de.marketingcockpit.events.shared.EventType;
import lulunpengpeng.de.marketingcockpit.events.shared.Location;

public interface MailingListEvent {
    String getMailingListId();
    Location getLocation();
    EventType getEventType();
}
