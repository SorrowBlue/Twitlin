package com.sorrowblue.twitlin.api.objects

import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.annotation.JvmSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Paging ids
 *
 * @param T
 * @property ids
 * @property nextCursor
 * @property previousCursor
 */
@AndroidParcelize
@Serializable
public data class PagingIds<T : AndroidParcelable>(
    val ids: List<T>,
    @SerialName("next_cursor_str")
    val nextCursor: String,
    @SerialName("previous_cursor_str")
    val previousCursor: String
) : AndroidParcelable, JvmSerializable
