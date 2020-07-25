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
import com.sorrowblue.twitlin.basics.AuthenticationApi
import com.sorrowblue.twitlin.basics.AuthenticationApiImp
import com.sorrowblue.twitlin.trends.TrendsApi
import com.sorrowblue.twitlin.trends.TrendsApiImp
import com.sorrowblue.twitlin.tweets.statuses.StatusesApi
import com.sorrowblue.twitlin.tweets.statuses.StatusesApiImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
internal abstract class TwitlinModule {
	@Binds
	abstract fun bindStatusApi(statusesApiImp: StatusesApiImp): StatusesApi

	@Binds
	abstract fun bindUsersApi(usersApiImp: UsersApiImp): UsersApi

	@Binds
	abstract fun bindListApi(listApiImp: ListsApiImp): ListsApi

	@Binds
	abstract fun bindFriendsApi(friendsApiImp: FriendsApiImp): FriendsApi

	@Binds
	abstract fun bindFollowersApi(followersApiImp: FollowersApiImp): FollowersApi

	@Binds
	abstract fun bindAccountApi(accountApiImp: AccountApiImp): AccountApi

	@Binds
	abstract fun bindAuthenticationApi(authenticationApiImp: AuthenticationApiImp): AuthenticationApi

	@Binds
	abstract fun bindTrendsApi(trendsApiImp: TrendsApiImp): TrendsApi
}
