package com.sorrowblue.twitlin

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
import com.sorrowblue.twitlin.basics.oauth.OAuthApi
import com.sorrowblue.twitlin.basics.oauth.OAuthApiImp
import com.sorrowblue.twitlin.basics.oauth2.OAuth2Api
import com.sorrowblue.twitlin.basics.oauth2.OAuth2ApiImp
import com.sorrowblue.twitlin.trends.TrendsApi
import com.sorrowblue.twitlin.trends.TrendsApiImp
import com.sorrowblue.twitlin.tweets.favorites.FavoritesApi
import com.sorrowblue.twitlin.tweets.favorites.FavoritesApiImp
import com.sorrowblue.twitlin.tweets.statuses.StatusesApi
import com.sorrowblue.twitlin.tweets.statuses.StatusesApiImp
import com.sorrowblue.twitlin.utilities.ApplicationApi
import com.sorrowblue.twitlin.utilities.ApplicationApiImp
import com.sorrowblue.twitlin.utilities.HelpApi
import com.sorrowblue.twitlin.utilities.HelpApiImp
import com.sorrowblue.twitlin.v2.tweets.TweetsApi
import com.sorrowblue.twitlin.v2.tweets.TweetsApiImp
import com.sorrowblue.twitlin.v2.users.TwitterAPIV2
import com.sorrowblue.twitlin.v2.users.UsersApi as V2UsersApi
import com.sorrowblue.twitlin.v2.users.UsersApiImp as V2UsersApiImp

object TwitterAPI {
	val oauth: OAuthApi by lazy { OAuthApiImp(Twitlin.client) }
	val oauth2: OAuth2Api by lazy { OAuth2ApiImp(Twitlin.client) }
	val trends: TrendsApi by lazy { TrendsApiImp(Twitlin.client) }
	val lists: ListsApi by lazy { ListsApiImp(Twitlin.client) }
	val account: AccountApi by lazy { AccountApiImp(Twitlin.client) }
	val statuses: StatusesApi by lazy { StatusesApiImp(Twitlin.client) }
	val users: UsersApi by lazy { UsersApiImp(Twitlin.client) }
	val followers: FollowersApi by lazy { FollowersApiImp(Twitlin.client) }
	val friends: FriendsApi by lazy { FriendsApiImp(Twitlin.client) }
	val help: HelpApi by lazy { HelpApiImp(Twitlin.client) }
	val application: ApplicationApi by lazy { ApplicationApiImp(Twitlin.client) }
	val favorites: FavoritesApi by lazy { FavoritesApiImp(Twitlin.client) }

	@TwitterAPIV2
	object V2 {

		val tweetsApi: TweetsApi by lazy { TweetsApiImp(Twitlin.v2Client) }
		val usersApi: V2UsersApi by lazy { V2UsersApiImp(Twitlin.v2Client) }
	}
}
