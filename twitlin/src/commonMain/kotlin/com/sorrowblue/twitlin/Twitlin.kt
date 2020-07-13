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

	private lateinit var _client: Client
	internal val client get() = _client

	private lateinit var _settings: Settings
	internal val settings: Settings get() = _settings

	/**
	 * TODO
	 *
	 * @param apiKey
	 * @param apiSecret
	 * @param settings
	 * @param oAuthToken
	 * @param oAuthTokenSecret
	 */
	@OptIn(ImplicitReflectionSerializer::class, UnstableDefault::class)
	fun initialize(
		apiKey: String, apiSecret: String,
		settings: Settings,
		oAuthToken: String? = null, oAuthTokenSecret: String? = null
	) {
		Napier.d("Twitlin has been initialized.", tag = "Twitlin")
		_settings = settings
		val accessToken = if (oAuthToken != null && oAuthTokenSecret != null) {
			AccessToken(oAuthToken, oAuthTokenSecret)
		} else {
			settings.getStringOrNull("twitlin_access_token")?.let { Json.parse<AccessToken>(it) }
		}
		_client = Client(apiKey, apiSecret, accessToken)
	}

	/**
	 * TODO
	 *
	 * @param apiKey
	 * @param apiSecret
	 * @param settings
	 * @param oAuthToken
	 * @param oAuthTokenSecret
	 * @return
	 */
	fun koinModule(
		apiKey: String, apiSecret: String,
		settings: Settings,
		oAuthToken: String? = null, oAuthTokenSecret: String? = null
	): Module {
		initialize(apiKey, apiSecret, settings, oAuthToken, oAuthTokenSecret)
		return module {
			single { settings }
			single { client }
			single { AuthenticationApiImp(get()) } bind AuthenticationApi::class
			single { AccountApiImp(get()) } bind AccountApi::class
			single { ListsApiImp(get()) } bind ListsApi::class
			single { TrendsApiImp(get()) } bind TrendsApi::class
			single { StatusesApiImp(get()) } bind StatusesApi::class
			single { UsersApiImp(get()) } bind UsersApi::class
		}
	}

	object Api {
		val authentication: AuthenticationApi by lazy { AuthenticationApiImp(client) }
		val trends: TrendsApi by lazy { TrendsApiImp(client) }
		val lists: ListsApi by lazy { ListsApiImp(client) }
		val account: AccountApi by lazy { AccountApiImp(client) }
		val statuses: StatusesApi by lazy { StatusesApiImp(client) }
		val users: UsersApi by lazy { UsersApiImp(client) }
	}

	val isAuthorizationRequired: Boolean get() = client.accessToken == null

	var onInvalidToken: () -> Unit = {}

}


