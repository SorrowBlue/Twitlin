package com.sorrowblue.twitlin.accounts_users.lists

import com.sorrowblue.twitlin.objects.TwitterUser
import com.sorrowblue.twitlin.serializers.DateTimeTzSerializer
import com.soywiz.klock.DateTimeTz
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TwitterList(
	val id: Long,
	@SerialName("id_str")
	val idStr: String,
	val name: String,
	val uri: String,
	@SerialName("subscriber_count")
	val subscriberCount: Int,
	@SerialName("member_count")
	val memberCount: Int,
	val mode: Mode,
	val description: String,
	val slug: String,
	@SerialName("full_name")
	val fullName: String,
	@SerialName("created_at")
	@Serializable(DateTimeTzSerializer::class)
	val createdAt: DateTimeTz,
	val following: Boolean,
	val user: TwitterUser

) {
	@Serializable
	enum class Mode {
		@SerialName("public")
		PUBLIC,

		@SerialName("private")
		PRIVATE
	}
}
