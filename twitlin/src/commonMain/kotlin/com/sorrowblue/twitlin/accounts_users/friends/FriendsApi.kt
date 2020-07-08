package com.sorrowblue.twitlin.accounts_users.friends

import com.sorrowblue.twitlin.accounts_users.UserIds
import com.sorrowblue.twitlin.net.Response
import com.sorrowblue.twitlin.objects.PagingUser

interface FriendsApi {

	suspend fun ids(
		userId: Long? = null,
		screenName: String? = null,
		cursor: Long = -1,
		stringifyIds: Boolean = false,
		count: Int? = null
	): Response<UserIds>


	suspend fun list(
		userId: Long? = null,
		screenName: String? = null,
		cursor: Long = -1,
		count: Int = 20,
		skipStatus: Boolean = false,
		includeUserEntities: Boolean = true
	): Response<PagingUser>
}
