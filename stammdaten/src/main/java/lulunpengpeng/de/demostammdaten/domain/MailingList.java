package lulunpengpeng.de.demostammdaten.domain;

import lombok.*;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class MailingList {

    @Id
    @GeneratedValue
    private UUID id;
    private String location;
    private String eventType;

    public MailingList(String location, String eventType) {
        this.location = location;
        this.eventType = eventType;
    }
}
