package lulunpengpeng.de.marketingcockpit.events.shared;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MailingList {
    @Id
    private String mailingListId;
    private Location location;
    private EventType eventType;
}
