package lulunpengpeng.de.demostammdaten.events;

import lombok.AllArgsConstructor;
import lombok.Value;
import lulunpengpeng.de.demostammdaten.dtos.EventTypeDTO;
import lulunpengpeng.de.demostammdaten.dtos.LocationDTO;

import java.io.Serializable;
import java.time.LocalDate;

@Value
@AllArgsConstructor
public class MailingListUpdatedEvent implements Serializable {
    private String mailingListId;
    private LocationDTO location;
    private EventTypeDTO eventType;
    private LocalDate updatedTime;
}
