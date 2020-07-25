package com.sorrowblue.twitlin

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.accounts_users.account.AccountApi
import com.sorrowblue.twitlin.accounts_users.account.AccountApiImp
import com.sorrowblue.twitlin.accounts_users.lists.ListsApi
import com.sorrowblue.twitlin.accounts_users.lists.ListsApiImp
import com.sorrowblue.twitlin.accounts_users.users.UsersApi
import com.sorrowblue.twitlin.accounts_users.users.UsersApiImp
import com.sorrowblue.twitlin.basics.AccessToken
import com.sorrowblue.twitlin.basics.oauth.OAuthApi
import com.sorrowblue.twitlin.basics.oauth.OAuthApiImp
import com.sorrowblue.twitlin.basics.oauth2.OAuth2Api
import com.sorrowblue.twitlin.basics.oauth2.OAuth2ApiImp
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

object Twitlin {

	internal lateinit var client: Client private set

	internal lateinit var settings: Settings private set

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
		this.settings = settings
		val accessToken = if (oAuthToken != null && oAuthTokenSecret != null) {
			AccessToken(oAuthToken, oAuthTokenSecret)
		} else {
//			settings.getString("twitlin_access_token", "")?.let { Json.parse<AccessToken>(it) }
			null
		}
		client = Client(apiKey, apiSecret, accessToken)
	}


	object Api {
		val oauth: OAuthApi by lazy { OAuthApiImp(client) }
		val oauth2: OAuth2Api by lazy { OAuth2ApiImp(client) }
		val trends: TrendsApi by lazy { TrendsApiImp(client) }
		val lists: ListsApi by lazy { ListsApiImp(client) }
		val account: AccountApi by lazy { AccountApiImp(client) }
		val statuses: StatusesApi by lazy { StatusesApiImp(client) }
		val users: UsersApi by lazy { UsersApiImp(client) }
	}

	val isAuthorizationRequired: Boolean get() = client.accessToken == null

	var onInvalidToken: () -> Unit = {}

	fun switchAccount() {

	}

}


