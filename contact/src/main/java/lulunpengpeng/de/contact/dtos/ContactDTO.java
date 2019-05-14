package lulunpengpeng.de.contact.dtos;

import lombok.Builder;
import lombok.Data;
import lulunpengpeng.de.contact.domain.Contact;

import java.io.Serializable;

@Data
@Builder
public class ContactDTO implements Serializable {

    private String contactId;
    private String firstName;
    private String lastName;
    private String email;
    private String company;

    public static ContactDTO fromContact(Contact contact) {
        return new ContactDTO(contact.getId().toString(), contact.getFirstName(), contact.getLastName(),
                contact.getEmail(), contact.getCompany());
    }
}
