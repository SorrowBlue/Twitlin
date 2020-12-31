/*
 * (c) 2020.
 */

/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.users

import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.objects.TwitterUser
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * TODO
 *
 * @property users
 * @property nextCursor
 * @property nextCursorStr
 * @property previousCursor
 * @property previousCursorStr
 */
@Serializable
public data class PagingUser(
    @SerialName("users") val users: List<TwitterUser>,
    @SerialName("next_cursor") val nextCursor: Long,
    @SerialName("next_cursor_str") val nextCursorStr: String,
    @SerialName("previous_cursor") val previousCursor: Long,
    @SerialName("previous_cursor_str") val previousCursorStr: String
) : JvmSerializable
