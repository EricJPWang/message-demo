package lulunpengpeng.de.marketingcockpit.events;

import java.time.LocalDate;

public interface ContactEvent {
    String getContactId();
    boolean isHasWarning();
    LocalDate getUpdatedTime();
}
