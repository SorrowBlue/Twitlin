/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.directmessages

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@AndroidParcelize
@Serializable
public data class PagingWelcomeMessage(
    @SerialName("next_cursor")
    val nextCursor: String? = null,
    @SerialName("welcome_messages")
    val welcomeMessages: List<WelcomeMessage>,
    val apps: Map<String, DirectMessageApp>
) : AndroidParcelable, JvmSerializable
