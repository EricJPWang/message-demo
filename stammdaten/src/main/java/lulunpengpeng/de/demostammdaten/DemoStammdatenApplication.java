package lulunpengpeng.de.demostammdaten;

import lulunpengpeng.de.demostammdaten.domain.EventType;
import lulunpengpeng.de.demostammdaten.domain.Location;
import lulunpengpeng.de.demostammdaten.domain.MailingList;
import lulunpengpeng.de.demostammdaten.dtos.EventTypeDTO;
import lulunpengpeng.de.demostammdaten.dtos.LocationDTO;
import lulunpengpeng.de.demostammdaten.events.MailingListCreatedEvent;
import lulunpengpeng.de.demostammdaten.messaging.StammdatenChannels;
import lulunpengpeng.de.demostammdaten.repositories.EventTypeRepository;
import lulunpengpeng.de.demostammdaten.repositories.LocationRepository;
import lulunpengpeng.de.demostammdaten.repositories.MailingListRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.stream.Stream;

import static java.time.LocalDate.now;
import static org.springframework.messaging.support.MessageBuilder.withPayload;

@SpringBootApplication
@EnableBinding(StammdatenChannels.class)
public class DemoStammdatenApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoStammdatenApplication.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner(MailingListRepository mailingListRepository,
										EventTypeRepository eventTypeRepository,
										LocationRepository locationRepository,
										StammdatenChannels stammdatenChannels) {
		return args -> {
			Stream.of("Stuttgart", "München", "Karlsruhe", "Frankfurt", "Köln")
					.map(Location::new)
					.forEach(locationRepository::save);
			Stream.of("Marketing", "Training")
					.map(EventType::new)
					.forEach(eventTypeRepository::save);

			List<Location> locations = locationRepository.findAll();
			List<EventType> eventTypes = eventTypeRepository.findAll();

			locations.stream().flatMap(
					location -> eventTypes.stream().map(
							eventType -> MailingList.builder().location(location).eventType(eventType).build())
			).forEach(mailingList -> {
				MailingList mailingListEntity = mailingListRepository.save(mailingList);
				MailingListCreatedEvent event = MailingListCreatedEvent.builder()
						.mailingListId(mailingListEntity.getId().toString())
						.updatedTime(now())
						.eventType(EventTypeDTO.fromEventType(mailingListEntity.getEventType()))
						.location(LocationDTO.fromLocation(mailingListEntity.getLocation()))
						.build();
				stammdatenChannels.mailingListCreatedOut().send(withPayload(event).build());
			});
		};

	}

}
