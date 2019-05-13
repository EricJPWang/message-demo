package lulunpengpeng.de.demostammdaten.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface StammdatenChannels {

    @Output
    MessageChannel mailingListCreatedOut();

    @Output
    MessageChannel mailingListRemovedOut();

    @Output
    MessageChannel mailingListUpdatedOut();
}
