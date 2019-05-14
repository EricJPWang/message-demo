package lulunpengpeng.de.marketingcockpit;

import lulunpengpeng.de.marketingcockpit.messaging.MarketingCockpitChannels;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableBinding(MarketingCockpitChannels.class)
public class MarketingcockpitApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketingcockpitApplication.class, args);
	}

}
