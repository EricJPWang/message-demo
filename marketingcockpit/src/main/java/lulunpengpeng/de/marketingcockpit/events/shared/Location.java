package lulunpengpeng.de.marketingcockpit.events.shared;

import lombok.*;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@Data
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(staticName = "from")
public class Location {
    @NotNull
    private String locationId;
    @NotNull
    private String locationName;

}
