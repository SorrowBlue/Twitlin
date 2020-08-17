package com.sorrowblue.twitlin

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.accounts_users.account.AccountApi
import com.sorrowblue.twitlin.accounts_users.account.AccountApiImp
import com.sorrowblue.twitlin.accounts_users.followers.FollowersApi
import com.sorrowblue.twitlin.accounts_users.followers.FollowersApiImp
import com.sorrowblue.twitlin.accounts_users.friends.FriendsApi
import com.sorrowblue.twitlin.accounts_users.friends.FriendsApiImp
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
import com.sorrowblue.twitlin.trends.TrendsApi
import com.sorrowblue.twitlin.trends.TrendsApiImp
import com.sorrowblue.twitlin.tweets.statuses.StatusesApi
import com.sorrowblue.twitlin.tweets.statuses.StatusesApiImp
import com.sorrowblue.twitlin.v2.tweets.TweetsApi
import com.sorrowblue.twitlin.v2.tweets.TweetsApiImp
import com.sorrowblue.twitlin.v2.Client as V2Client

object Twitlin {

	internal lateinit var client: Client private set
	internal lateinit var v2Client: V2Client private set

	var accessToken: AccessToken?
		get() = client.accessToken
		set(value) {
			client.accessToken = value
			v2Client.accessToken = value
		}

	fun initialize(apiKey: String, apiSecret: String, accessToken: AccessToken? = null) {
		Napier.d("Twitlin has been initialized.", tag = "Twitlin")
		client = Client(apiKey, apiSecret, accessToken)
		v2Client = V2Client(apiKey, apiSecret, accessToken)
	}


	object Api {
		val oauth: OAuthApi by lazy { OAuthApiImp(client) }
		val oauth2: OAuth2Api by lazy { OAuth2ApiImp(client) }
		val trends: TrendsApi by lazy { TrendsApiImp(client) }
		val lists: ListsApi by lazy { ListsApiImp(client) }
		val account: AccountApi by lazy { AccountApiImp(client) }
		val statuses: StatusesApi by lazy { StatusesApiImp(client) }
		val users: UsersApi by lazy { UsersApiImp(client) }
		val followers: FollowersApi by lazy { FollowersApiImp(client) }
		val friends: FriendsApi by lazy { FriendsApiImp(client) }
	}
	object v2 {
		val usersApi: com.sorrowblue.twitlin.v2.users.UsersApi by lazy {
			com.sorrowblue.twitlin.v2.users.UsersApiImp(v2Client)
		}
		val tweetsApi: TweetsApi by lazy { TweetsApiImp(v2Client) }
	}

	val isAuthorizationRequired: Boolean get() = client.accessToken == null

	var onInvalidToken: () -> Unit = {}
}


