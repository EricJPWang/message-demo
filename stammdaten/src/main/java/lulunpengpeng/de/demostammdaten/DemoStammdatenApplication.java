package lulunpengpeng.de.demostammdaten;

import lulunpengpeng.de.demostammdaten.messaging.StammdatenChannels;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(StammdatenChannels.class)
public class DemoStammdatenApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoStammdatenApplication.class, args);
	}

}
