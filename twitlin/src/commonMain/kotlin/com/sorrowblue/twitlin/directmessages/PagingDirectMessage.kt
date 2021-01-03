/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.directmessages

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class PagingDirectMessage(
    @SerialName("next_cursor")
    val nextCursor: String? = null,
    val events: List<DirectMessageEvent>,
    val apps: Map<String, DirectMessageApp>
)
