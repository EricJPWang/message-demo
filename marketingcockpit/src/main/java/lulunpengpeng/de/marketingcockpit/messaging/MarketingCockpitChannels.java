package lulunpengpeng.de.marketingcockpit.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MarketingCockpitChannels {

    String MAILING_LIST_CREATED_IN = "mailingListCreatedIn";
    String MAILING_LIST_UPDATED_IN = "mailingListUpdatedIn";
    String MAILING_LIST_REMOVED_IN = "mailingListRemovedIn";
    String CONTACT_CREATED_IN = "contactCreatedIn";
    String CONTACT_UPDATED_IN = "contactUpdatedIn";
    String CONTACT_REMOVED_IN = "contactRemovedIn";

    @Input
    SubscribableChannel mailingListCreatedIn();

    @Input
    SubscribableChannel mailingListUpdatedIn();

    @Input
    SubscribableChannel mailingListRemovedIn();

    @Input
    SubscribableChannel contactCreatedIn();

    @Input
    SubscribableChannel contactUpdatedIn();

    @Input
    SubscribableChannel contactRemovedIn();
}
