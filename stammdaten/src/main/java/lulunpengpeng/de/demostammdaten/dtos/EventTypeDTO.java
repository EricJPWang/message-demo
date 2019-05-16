package lulunpengpeng.de.demostammdaten.dtos;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lulunpengpeng.de.demostammdaten.domain.EventType;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class EventTypeDTO implements Serializable {
    private String eventTypeId;
    private String eventTypeName;

    public static EventTypeDTO fromEventType(EventType eventType) {
        return new EventTypeDTO((eventType.getId().toString()), eventType.getName());
    }
}
