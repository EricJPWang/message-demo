package lulunpengpeng.de.contact.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnTransformer;
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

    @ColumnTransformer(read = "pgp_sym_decrypt(first_name::bytea, 'mySecretKey')", write = "pgp_sym_encrypt(?, 'mySecretKey')")
    private String firstName;
    @ColumnTransformer(read = "pgp_sym_decrypt(last_name::bytea, 'mySecretKey')", write = "pgp_sym_encrypt(?, 'mySecretKey')")
    private String lastName;
    @ColumnTransformer(read = "pgp_sym_decrypt(email::bytea, 'mySecretKey')", write = "pgp_sym_encrypt(?, 'mySecretKey')")
    private String email;
    @ColumnTransformer(read = "pgp_sym_decrypt(company::bytea, 'mySecretKey')", write = "pgp_sym_encrypt(?, 'mySecretKey')")
    private String company;

    public Contact(String firstName, String lastName, String email, String company) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.company = company;
    }
    
    
    public boolean hasWarning() {
        return Stream.of(firstName, lastName, email, company).anyMatch(StringUtils::isEmpty);
    }
}
