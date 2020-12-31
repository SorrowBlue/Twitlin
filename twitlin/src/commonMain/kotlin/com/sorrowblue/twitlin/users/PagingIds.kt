/*
 * (c) 2020.
 */

/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.users

import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * TODO
 *
 * @property ids
 * @property nextCursor
 * @property nextCursorStr
 * @property previousCursor
 * @property previousCursorStr
 */
@Serializable
public data class PagingIds(
    val ids: List<String>,
    @SerialName("next_cursor") val nextCursor: Long,
    @SerialName("next_cursor_str") val nextCursorStr: String,
    @SerialName("previous_cursor") val previousCursor: Long,
    @SerialName("previous_cursor_str") val previousCursorStr: String
) : JvmSerializable
