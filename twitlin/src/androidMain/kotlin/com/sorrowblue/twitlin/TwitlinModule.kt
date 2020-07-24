package com.sorrowblue.twitlin

import com.sorrowblue.twitlin.basics.AuthenticationApi
import com.sorrowblue.twitlin.basics.AuthenticationApiImp
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
	abstract fun bindAuthenticationApi(authenticationApiImp: AuthenticationApiImp): AuthenticationApi
}