package lulunpengpeng.de.contact.events;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

@Value
public class ContactRemovedEvent implements Serializable {
    private String contactId;
    private LocalDate updatedTime;
}
