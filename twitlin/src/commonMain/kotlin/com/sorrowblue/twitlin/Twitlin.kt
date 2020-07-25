package com.sorrowblue.twitlin

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.accounts_users.account.AccountApi
import com.sorrowblue.twitlin.accounts_users.account.AccountApiImp
import com.sorrowblue.twitlin.accounts_users.lists.ListsApi
import com.sorrowblue.twitlin.accounts_users.lists.ListsApiImp
import com.sorrowblue.twitlin.accounts_users.users.UsersApi
import com.sorrowblue.twitlin.accounts_users.users.UsersApiImp
import com.sorrowblue.twitlin.basics.AccessToken
import com.sorrowblue.twitlin.basics.AuthenticationApi
import com.sorrowblue.twitlin.basics.AuthenticationApiImp
import com.sorrowblue.twitlin.net.Client
import com.sorrowblue.twitlin.settings.Settings
import com.sorrowblue.twitlin.trends.TrendsApi
import com.sorrowblue.twitlin.trends.TrendsApiImp
import com.sorrowblue.twitlin.tweets.statuses.StatusesApi
import com.sorrowblue.twitlin.tweets.statuses.StatusesApiImp
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.parse
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

object Twitlin {

	internal lateinit var client: Client private set

	internal lateinit var settings: Settings private set

	@OptIn(ImplicitReflectionSerializer::class, UnstableDefault::class)
	fun initialize(
		apiKey: String, apiSecret: String,
		settings: Settings,
		oAuthToken: String? = null, oAuthTokenSecret: String? = null
	) {
		Napier.d("Twitlin has been initialized.", tag = "Twitlin")
		this.settings = settings
		val accessToken = if (oAuthToken != null && oAuthTokenSecret != null) {
			AccessToken(oAuthToken, oAuthTokenSecret)
		} else {
			settings.getStringSetOrNull("twitlin_access_tokens")
				?.map { Json.parse<AccessToken>(it) }
		}
		client = Client(apiKey, apiSecret, accessToken)
	}

	object Api {
		val authentication: AuthenticationApi get() = AuthenticationApiImp(client)
		val trends: TrendsApi get() = TrendsApiImp(client)
		val lists: ListsApi get() = ListsApiImp(client)
		val account: AccountApi get() = AccountApiImp(client)
		val statuses: StatusesApi get() = StatusesApiImp(client)
		val users: UsersApi get() = UsersApiImp(client)
	}

	val isAuthorizationRequired: Boolean get() = client.accessToken == null

	var onInvalidToken: () -> Unit = {}

}


