package com.sorrowblue.twitlin.api.directmessages

import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.annotation.JvmSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@AndroidParcelize
@Serializable
public data class PagingDirectMessage(
    @SerialName("next_cursor")
    val nextCursor: String? = null,
    val events: List<DirectMessageEvent> = emptyList(),
    val apps: Map<String, DirectMessageApp> = emptyMap()
) : AndroidParcelable, JvmSerializable
