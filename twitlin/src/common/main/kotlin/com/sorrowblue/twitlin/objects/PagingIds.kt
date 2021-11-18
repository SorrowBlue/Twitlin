package com.sorrowblue.twitlin.objects

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Paging ids
 *
 * @param T
 * @property ids
 * @property nextCursor
 * @property nextCursorStr
 * @property previousCursor
 * @property previousCursorStr
 * @constructor Create empty Paging ids
 */
@AndroidParcelize
@Serializable
public data class PagingIds<T : AndroidParcelable>(
    val ids: List<T>,
    @SerialName("next_cursor") val nextCursor: Long,
    @SerialName("next_cursor_str") val nextCursorStr: String,
    @SerialName("previous_cursor") val previousCursor: Long,
    @SerialName("previous_cursor_str") val previousCursorStr: String
) : AndroidParcelable, JvmSerializable
