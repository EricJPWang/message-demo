package lulunpengpeng.de.contact;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import lulunpengpeng.de.contact.domain.Contact;
import lulunpengpeng.de.contact.events.ContactRemovedEvent;
import lulunpengpeng.de.contact.events.ContactUpdatedEvent;
import lulunpengpeng.de.contact.messaging.ContactMessagingService;
import lulunpengpeng.de.contact.repositories.ContactRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest(classes = ContactApplication.class)
@RunWith(SpringRunner.class)
@AutoConfigureMessageVerifier
public abstract class BaseClass {

    private static final String UUID_FOR_TESTING = "56fc607a-cbdf-42df-83b7-9737ef1a6dc7";

    @Autowired
    private ContactMessagingService contactMessagingService;

    @MockBean
    private ContactRepository repository;

    public void triggerUpdateMessage() {
        this.contactMessagingService.sendContactUpdatedEvent(new ContactUpdatedEvent(UUID_FOR_TESTING, false,
                LocalDate.of(1970, 1, 1)));
    }

    public void triggerRemovedMessage() {
        this.contactMessagingService.sendContactRemovedEvent(new ContactRemovedEvent(UUID_FOR_TESTING,
                LocalDate.of(1970, 1, 1)));
    }

    @Before
    public void setUp() {
        RestAssuredMockMvc.standaloneSetup(this.contactMessagingService);
        Mockito.when(repository.findById(UUID.fromString(UUID_FOR_TESTING))).thenReturn(
               Optional.of(newContact())
        );
    }

    private Contact newContact() {
        Contact contact = Contact.builder().build();
        contact.setId(UUID.fromString(UUID_FOR_TESTING));
        return contact;
    }
}
