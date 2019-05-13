package lulunpengpeng.de.contact.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;
import java.util.stream.Stream;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Contact {
    @Id
    @GeneratedValue
    private UUID id;

    private String firstName;
    private String lastName;
    private String email;
    private String company;

    public Contact(String firstName, String lastName, String email, String company) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.company = company;
    }
    
    
    public boolean hasWarning() {
        return Stream.of(firstName, lastName, email, company).allMatch(StringUtils::isEmpty);
    }
}
