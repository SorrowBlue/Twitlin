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
import org.koin.core.KoinComponent
import org.koin.core.get
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

internal expect fun Module.settingsModule()

fun twitlinModule(apiKey: String, apiSecret: String) =
	module {
		settingsModule()
		single { Client(apiKey, apiSecret) }
		single { AuthenticationApiImp(get()) } bind AuthenticationApi::class
		single { AccountApiImp(get()) } bind AccountApi::class
		single { ListsApiImp(get()) } bind ListsApi::class
		single { TrendsApiImp(get()) } bind TrendsApi::class
		single { StatusesApiImp(get()) } bind StatusesApi::class
		single { UsersApiImp(get()) } bind UsersApi::class
	}


object Twitlin : KoinComponent {

	internal val client: Client by lazy { get<Client>() }
	internal val settings: Settings by lazy { get<Settings>() }

	@OptIn(ImplicitReflectionSerializer::class, UnstableDefault::class)
	fun initialize(oAuthToken: String? = null, oAuthTokenSecret: String? = null) {
		Napier.d("Twitlin has been initialized.", tag = "Twitlin")
		val accessToken = if (oAuthToken != null && oAuthTokenSecret != null) {
			AccessToken(oAuthToken, oAuthTokenSecret)
		} else {
			settings.getStringOrNull("twitlin_access_token")?.let { Json.parse<AccessToken>(it) }
		}
		client.accessToken = accessToken
	}

	object api {
		val authentication: AuthenticationApi by lazy { get<AuthenticationApi>() }
		val trends: TrendsApi by lazy { get<TrendsApi>() }
		val lists: ListsApi by lazy { get<ListsApi>() }
		val account: AccountApi by lazy { get<AccountApi>() }
		val statuses: StatusesApi by lazy { get<StatusesApi>() }
		val users: UsersApi by lazy { get<UsersApi>() }
	}

	val isAuthorizationRequired: Boolean get() = client.accessToken == null

	var onInvalidToken: () -> Unit = {}

}


