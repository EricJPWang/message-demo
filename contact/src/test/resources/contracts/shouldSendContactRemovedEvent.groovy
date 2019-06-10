package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should send contact removed event"
    label "contact_removed"
    input {
        triggeredBy("triggerRemovedMessage()")
    }
    outputMessage {
        sentTo "ContactRemovedTopic"
        body(
                [
                        contactId  : "56fc607a-cbdf-42df-83b7-9737ef1a6dc7",
                        updatedTime: "1970-01-01"
                ]

        )
    }
}

