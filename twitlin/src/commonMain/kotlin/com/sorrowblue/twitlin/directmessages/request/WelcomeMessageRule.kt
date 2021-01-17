/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.directmessages.request

import kotlinx.serialization.Serializable

@Serializable
internal class WelcomeMessageRuleRequest(val welcome_message_rule: WelcomeMessageRule) {

    @Serializable
    class WelcomeMessageRule(val welcome_message_id: String)
}
