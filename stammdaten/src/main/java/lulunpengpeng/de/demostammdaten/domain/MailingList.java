package lulunpengpeng.de.demostammdaten.domain;

import lombok.*;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor
@Builder
public class MailingList {

    @Id
    @GeneratedValue
    private UUID id;

    @JoinColumn
    @ManyToOne
    private Location location;

    @JoinColumn
    @ManyToOne
    private EventType eventType;
}
