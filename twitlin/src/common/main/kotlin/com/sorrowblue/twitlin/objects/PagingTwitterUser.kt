/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.objects

import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class PagingTwitterUser(
    val users: List<User>,
    @SerialName("next_cursor")
    val nextCursor: Long,
    @SerialName("next_cursor_str")
    val nextCursorStr: String,
    @SerialName("previous_cursor")
    val previousCursor: Long,
    @SerialName("previous_cursor_str")
    val previousCursorStr: String,
    @SerialName("total_count")
    val totalCount: Int? = null
) : JvmSerializable
