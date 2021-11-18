package com.sorrowblue.twitlin.directmessages.request

import kotlinx.serialization.Serializable

@Serializable
internal class DirectMessageRequest(val event: Event) {

    @Serializable
    internal class Event(val type: String, val message_create: MessageCreate) {

        @Serializable
        data class MessageCreate(val target: Target, val message_data: MessageDataRequest) {

            @Serializable
            data class Target(val recipient_id: String)
        }
    }
}
