/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.directmessages

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class PagingWelcomeMessage(
    @SerialName("next_cursor")
    val nextCursor: String? = null,
    @SerialName("welcome_messages")
    val welcomeMessages: List<WelcomeMessage>,
    val apps: Map<String, DirectMessageApp>
)
