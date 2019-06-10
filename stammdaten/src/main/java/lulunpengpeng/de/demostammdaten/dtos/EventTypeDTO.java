package lulunpengpeng.de.demostammdaten.dtos;

import lombok.*;
import lulunpengpeng.de.demostammdaten.domain.EventType;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class EventTypeDTO implements Serializable {
    private String eventTypeId;
    private String eventTypeName;

    public static EventTypeDTO fromEventType(EventType eventType) {
        return new EventTypeDTO((eventType.getId().toString()), eventType.getName());
    }
}
