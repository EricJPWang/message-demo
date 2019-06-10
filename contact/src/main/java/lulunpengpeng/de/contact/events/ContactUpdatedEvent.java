package lulunpengpeng.de.contact.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

@Value
public class ContactUpdatedEvent implements Serializable, ContactEvent {
    private String contactId;
    private boolean hasWarning;
    private LocalDate updatedTime;
}
