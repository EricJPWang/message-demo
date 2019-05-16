package lulunpengpeng.de.marketingcockpit.events.shared;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class Contact {

    @Id
    private String contactId;
    private boolean hasWarning;
}
