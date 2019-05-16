package lulunpengpeng.de.demostammdaten.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class EventType {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;

    public EventType(String name) {
        this.name = name;
    }
}
