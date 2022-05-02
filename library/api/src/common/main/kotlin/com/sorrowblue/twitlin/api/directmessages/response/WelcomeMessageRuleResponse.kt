package com.sorrowblue.twitlin.api.directmessages.response

import com.sorrowblue.twitlin.api.directmessages.WelcomeMessageRule
import kotlinx.serialization.Serializable

@Serializable
internal class WelcomeMessageRuleResponse(
    val welcome_message_rule: WelcomeMessageRule
)
