package lulunpengpeng.de.contact;

import lulunpengpeng.de.contact.messaging.ContactChannels;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableBinding(ContactChannels.class)
public class ContactApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ContactRepository repository) {

	}


}
