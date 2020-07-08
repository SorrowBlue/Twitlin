package com.sorrowblue.twitlin.accounts_users

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * TODO
 *
 * @property ids ユーザIDリスト
 * @property nextCursor 次のページの数値カーソル
 * @property nextCursorStr 次のページの文字列カーソル
 * @property previousCursor 前のページの数値カーソル
 * @property previousCursorStr 前のページの文字列カーソル
 */
@Serializable
data class UserIds(
	val ids: List<Long>,
	@SerialName("next_cursor") val nextCursor: Long,
	@SerialName("next_cursor_str") val nextCursorStr: String,
	@SerialName("previous_cursor") val previousCursor: Long,
	@SerialName("previous_cursor_str") val previousCursorStr: String
)
