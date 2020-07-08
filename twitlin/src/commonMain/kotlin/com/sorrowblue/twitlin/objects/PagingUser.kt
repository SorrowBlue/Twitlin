package com.sorrowblue.twitlin.objects

import com.sorrowblue.twitlin.AndroidParcel
import com.sorrowblue.twitlin.AndroidParcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@AndroidParcelize
@Serializable
data class PagingUser(
	val users: List<TwitterUser>,
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
) : AndroidParcel
