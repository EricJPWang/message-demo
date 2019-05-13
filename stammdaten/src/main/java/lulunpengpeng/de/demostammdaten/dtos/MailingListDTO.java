package lulunpengpeng.de.demostammdaten.dtos;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class MailingListDTO {
    private String mailingListId;
    private String location;
    private String eventType;
}
