package com.sorrowblue.twitlin.api.directmessages.request

import kotlinx.serialization.Serializable

@Serializable
internal class WelcomeMessageRuleRequest(val welcome_message_rule: WelcomeMessageRule) {

    @Serializable
    class WelcomeMessageRule(val welcome_message_id: String)
}
