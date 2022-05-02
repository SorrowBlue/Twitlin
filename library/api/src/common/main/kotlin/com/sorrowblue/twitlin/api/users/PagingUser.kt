package com.sorrowblue.twitlin.api.users

import com.sorrowblue.twitlin.api.objects.User
import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.annotation.JvmSerializable
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
@AndroidParcelize
@Serializable
public data class PagingUser(
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
) : AndroidParcelable, JvmSerializable
