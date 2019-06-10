package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should send a mailinglist removed event"
    label "mailinglist_removed"
    input {
        triggeredBy("triggerMailinglistRemovedEvent()")
    }

    outputMessage {
        sentTo "MailingListRemovedTopic"
        body(
                ''' {
                    "mailingListId" : "mailinglist_id",
                    "updatedTime" : "1970-01-01"
                }
                '''
        )
    }
}
