package lulunpengpeng.de.demostammdaten.dtos;

import lombok.*;
import lulunpengpeng.de.demostammdaten.domain.Location;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class LocationDTO implements Serializable {
    private String locationId;
    private String locationName;

    public static LocationDTO fromLocation(Location location) {
        return new LocationDTO(location.getId().toString(), location.getName());
    }

}
