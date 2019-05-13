package lulunpengpeng.de.contact.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ContactChannels {
    @Output
    MessageChannel contactCreatedOut();

    @Output
    MessageChannel contactRemovedOut();

    @Output
    MessageChannel contactUpdatedOut();
}
