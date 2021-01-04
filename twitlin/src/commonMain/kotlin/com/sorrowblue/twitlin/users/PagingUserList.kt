/*
 * (c) 2021 SorrowBlue.
 */

/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.users

import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * TODO
 *
 * @property lists
 * @property nextCursor
 * @property nextCursorStr
 * @property previousCursor
 * @property previousCursorStr
 * @property totalCount
 */
@Serializable
public data class PagingUserList(
    val lists: List<UserList>,
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
