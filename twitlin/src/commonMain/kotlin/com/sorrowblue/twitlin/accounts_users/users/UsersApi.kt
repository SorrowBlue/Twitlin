package com.sorrowblue.twitlin.accounts_users.users

import com.sorrowblue.twitlin.net.Response
import com.sorrowblue.twitlin.objects.TwitterUser

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
}
