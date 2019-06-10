package contracts

import org.springframework.cloud.contract.spec.Contract
import java.time.LocalDate

Contract.make {
    description "should send a contact updated event"
    label "contact_updated"

    input {
        triggeredBy("triggerUpdateMessage()")
    }

    outputMessage {
        sentTo "ContactUpdatedTopic"
        body(
                [
                        contactId  : "56fc607a-cbdf-42df-83b7-9737ef1a6dc7",
                        hasWarning : false,
                        updatedTime: "1970-01-01"
                ]

        )
    }

}