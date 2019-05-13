package lulunpengpeng.de.marketingcockpit.events;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

@Value
public class MailingListCreatedEvent implements Serializable {
    private String mailingListId;
    private String eventType;
    private String location;
    private LocalDate updatedTime;
}
