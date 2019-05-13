package lulunpengpeng.de.contact;

import lulunpengpeng.de.contact.domain.Contact;
import lulunpengpeng.de.contact.messaging.ContactChannels;
import lulunpengpeng.de.contact.repositories.ContactRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
@EnableBinding(ContactChannels.class)
public class ContactApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ContactRepository repository) {
		return args -> {
			Stream.of(
					new Contact("firstName_1", "lastName_1", "email_1", "company_1"),
					new Contact("firstName_2", "lastName_2", "email_2", "company_2"),
					new Contact("firstName_3", "lastName_3", "email_3", "company_3"),
					new Contact("firstName_4", "lastName_4", "email_4", "company_4")
			).forEach(repository::save);
		};
	}


}
