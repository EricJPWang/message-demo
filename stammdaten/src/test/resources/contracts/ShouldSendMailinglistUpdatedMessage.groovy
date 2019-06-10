package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("should send mailinglist updated message")
    label("mailinglist_updated")
    input {
        triggeredBy("triggerMailinglistUpdatedEvent()")
    }

    outputMessage {
        sentTo("MailingListUpdatedTopic")
        body('''
            {
                "mailingListId" : "mailinglist_id",
                "location" : {
                    "locationId" : "location_id",
                    "locationName" : "location_name"
                },
                "eventType" : {
                    "eventTypeId" : "eventType_id",
                    "eventTypeName" : "eventType_name"
                },
                "updatedTime" : "1970-01-01"
            }
            ''')
    }
}