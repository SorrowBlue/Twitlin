package com.sorrowblue.twitlin.directmessages

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@AndroidParcelize
@Serializable
public data class PagingDirectMessage(
    @SerialName("next_cursor")
    val nextCursor: String? = null,
    val events: List<DirectMessageEvent>,
    val apps: Map<String, DirectMessageApp>
) : AndroidParcelable, JvmSerializable
