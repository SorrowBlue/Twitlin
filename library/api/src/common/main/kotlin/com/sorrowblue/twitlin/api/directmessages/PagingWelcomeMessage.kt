package com.sorrowblue.twitlin.api.directmessages

import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.annotation.JvmSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@AndroidParcelize
@Serializable
public data class PagingWelcomeMessage(
    @SerialName("next_cursor")
    val nextCursor: String? = null,
    @SerialName("welcome_messages")
    val welcomeMessages: List<WelcomeMessage> = emptyList(),
    val apps: Map<String, DirectMessageApp> = emptyMap()
) : AndroidParcelable, JvmSerializable
