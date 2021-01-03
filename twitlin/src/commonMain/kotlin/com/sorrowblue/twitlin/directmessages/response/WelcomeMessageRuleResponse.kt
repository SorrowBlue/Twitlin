/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.directmessages.response

import com.sorrowblue.twitlin.directmessages.WelcomeMessageRule
import kotlinx.serialization.Serializable

@Serializable
internal class WelcomeMessageRuleResponse(
    val welcome_message_rule: WelcomeMessageRule
)
