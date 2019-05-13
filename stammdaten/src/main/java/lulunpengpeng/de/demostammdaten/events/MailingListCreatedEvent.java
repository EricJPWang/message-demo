package lulunpengpeng.de.demostammdaten.events;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

@Value
@AllArgsConstructor
public class MailingListCreatedEvent implements Serializable {
    private String mailingListId;
    private LocalDate updatedTime;
}
