package lulunpengpeng.de.contact.events;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

@Value
@AllArgsConstructor
public class ContactCreatedEvent implements Serializable {
    private String contactId;
    private boolean hasWarning;
    private LocalDate updatedTime;
}
