package com.sorrowblue.twitlin.accounts_users.users

import com.sorrowblue.twitlin.net.Response
import com.sorrowblue.twitlin.objects.TwitterUser
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

interface UsersApi {

	suspend fun lookup(
		vararg screenNames: String,
		includeEntities: Boolean? = null,
		tweetMode: Mode? = null
	): Response<List<TwitterUser>>

	suspend fun lookup(
		vararg userIds: Long,
		includeEntities: Boolean? = null,
		tweetMode: Mode? = null
	): Response<List<TwitterUser>>

	enum class Mode(val value: String) {
		COMPAT("compat"),
		EXTENDED("extended")
	}

	suspend fun profileBanner(userId: Long): Response<ProfileBanner>
}

@Serializable
data class ProfileBanner(
	val sizes: Sizes
) {
	@Serializable
	data class Sizes(
		val ipad: Device,
		val ipad_retina: Device,
		val web: Device,
		val web_retina: Device,
		val mobile: Device,
		val mobile_retina: Device,
		@SerialName("300x100")
		val _300x100: Device,
		@SerialName("600x200")
		val _600x200: Device,
		@SerialName("1500x500")
		val _1500x500: Device,

		) {
		@Serializable
		data class Device(
			val h: Int,
			val w: Int,
			val url: String
		)
	}
}
