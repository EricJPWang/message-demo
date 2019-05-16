package lulunpengpeng.de.demostammdaten.dtos;

import lombok.*;
import lulunpengpeng.de.demostammdaten.domain.MailingList;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class MailingListDTO {
    private String mailingListId;
    private String location;
    private String eventType;

    public static MailingListDTO from(MailingList mailingList) {
        return new MailingListDTO(mailingList.getId().toString(),
                mailingList.getLocation().getName(),
                mailingList.getEventType().getName());
    }
}
