/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.directmessages

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class WelcomeMessageData(
    @SerialName("welcome_message")
    val welcomeMessage: WelcomeMessage,
    val apps: Map<String, DirectMessageApp>
)
