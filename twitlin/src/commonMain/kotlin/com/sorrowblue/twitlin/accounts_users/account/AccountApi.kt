package com.sorrowblue.twitlin.accounts_users.account

import com.sorrowblue.twitlin.net.Response
import com.sorrowblue.twitlin.objects.TwitterUser

interface AccountApi {

	suspend fun settings(): Response<TwitterSettings>
	suspend fun verifyCredentials(
		includeEntities: Boolean = true,
		skipStatus: Boolean = false,
		includeEmail: Boolean = false
	): Response<TwitterUser>
}
