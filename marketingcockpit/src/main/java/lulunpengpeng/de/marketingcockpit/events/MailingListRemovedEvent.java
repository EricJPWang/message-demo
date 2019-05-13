package lulunpengpeng.de.marketingcockpit.events;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

@Value
public class MailingListRemovedEvent implements Serializable {
    private String mailingListId;
    private LocalDate updatedTime;
}
