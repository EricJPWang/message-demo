package lulunpengpeng.de.marketingcockpit.domain.shared;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lulunpengpeng.de.marketingcockpit.domain.persist.MailinglistContactMapper;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MailingList {
    @Id
    private String mailingListId;
    private String location;
    private String eventType;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<MailinglistContactMapper>  mailinglistContactMappers;
}
