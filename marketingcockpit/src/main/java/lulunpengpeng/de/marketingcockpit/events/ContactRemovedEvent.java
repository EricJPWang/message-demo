package lulunpengpeng.de.marketingcockpit.events;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

@Value
public class ContactRemovedEvent implements ContactEvent, Serializable {
    private String contactId;
    private boolean hasWarning;
    private LocalDate updatedTime;
}
