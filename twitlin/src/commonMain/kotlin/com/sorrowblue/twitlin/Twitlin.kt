package com.sorrowblue.twitlin

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.accounts_users.account.AccountApi
import com.sorrowblue.twitlin.accounts_users.account.AccountApiImp
import com.sorrowblue.twitlin.accounts_users.lists.ListsApi
import com.sorrowblue.twitlin.accounts_users.lists.ListsApiImp
import com.sorrowblue.twitlin.accounts_users.users.UsersApi
import com.sorrowblue.twitlin.accounts_users.users.UsersApiImp
import com.sorrowblue.twitlin.basics.oauth.AccessToken
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
import kotlinx.serialization.stringify

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
		settings.getStringSet("accounts", null)?.map { Json.parse<Account>(it) }
			?.also { accounts = it }
		val account = if (oAuthToken != null && oAuthTokenSecret != null) {
			Account(0, "", "", "", AccessToken(oAuthToken, oAuthTokenSecret))
		} else {
			settings.getString("now_account", null)?.let { Json.parse<Account>(it) }
		}
		client = Client(apiKey, apiSecret, account)
		this.account = account
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

	val isAuthorizationRequired: Boolean get() = client.account == null

	var onInvalidToken: () -> Unit = {}

	var accounts: List<Account> = emptyList()
		internal set

	@OptIn(ImplicitReflectionSerializer::class, UnstableDefault::class)
	var account: Account?
		get() = client.account
		set(value) {
			value ?: return
			if (accounts.any { it.id == value.id }) {
				client.account = value
				Napier.d("Account changed to @${value.name}.", tag = "Twitlin")
			} else {
				accounts = accounts + value
				client.account = value
				Napier.d("Account added and changed to @${value.name}.", tag = "Twitlin")
			}
			settings.putString("now_account", Json.stringify(value))
			settings.putStringSet("accounts", accounts.map { Json.stringify(it) }.toSet())
			Napier.d(
				"""The currently stored accounts are as follows.
				${accounts.joinToString("\n")}
			""".trimIndent()
			)
		}
}


