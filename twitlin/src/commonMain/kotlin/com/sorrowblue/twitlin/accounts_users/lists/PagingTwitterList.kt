package com.sorrowblue.twitlin.accounts_users.lists

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PagingTwitterList(
    val lists: List<TwitterList>,
    @SerialName("next_cursor")
    val nextCursor: Long,
    @SerialName("next_cursor_str")
    val nextCursorStr: String,
    @SerialName("previous_cursor")
    val previousCursor: Long,
    @SerialName("previous_cursor_str")
    val previousCursorStr: String
)
