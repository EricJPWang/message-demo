package lulunpengpeng.de.demostammdaten.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lulunpengpeng.de.demostammdaten.dtos.EventTypeDTO;
import lulunpengpeng.de.demostammdaten.dtos.LocationDTO;

import java.io.Serializable;
import java.time.LocalDate;

@Value
@AllArgsConstructor
@Builder
public class MailingListUpdatedEvent implements Serializable, MailinglistEvent {
    private String mailingListId;
    private LocationDTO location;
    private EventTypeDTO eventType;
    private LocalDate updatedTime;
}
