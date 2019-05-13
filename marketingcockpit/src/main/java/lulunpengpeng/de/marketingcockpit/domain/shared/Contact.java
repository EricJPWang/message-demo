package lulunpengpeng.de.marketingcockpit.domain.shared;

import lombok.*;
import lulunpengpeng.de.marketingcockpit.domain.persist.MailinglistContactMapper;

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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<MailinglistContactMapper> mailinglistContactMappers;
}
