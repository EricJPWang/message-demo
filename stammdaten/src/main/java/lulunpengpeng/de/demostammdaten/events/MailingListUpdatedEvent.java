package lulunpengpeng.de.demostammdaten.events;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

@Value
@AllArgsConstructor
public class MailingListUpdatedEvent implements Serializable {
    private String mailingListId;
    private String location;
    private String eventType;
    private LocalDate updatedTime;
}
