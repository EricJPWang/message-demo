package lulunpengpeng.de.contact.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class ContactDTO implements Serializable {

    private String contactId;
    private String firstName;
    private String lastName;
    private String email;
    private String company;
}
