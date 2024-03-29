package com.sorrowblue.twitlin.api.directmessages.request

import kotlinx.serialization.Serializable

@Serializable
internal class WelcomeMessageRequest(val welcome_message: WelcomeMessage) {

    @Serializable
    class WelcomeMessage(val name: String, val message_data: MessageDataRequest)
}
