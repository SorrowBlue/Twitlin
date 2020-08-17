package com.sorrowblue.twitlin

import com.sorrowblue.twitlin.v2.tweets.TweetsApi
import com.sorrowblue.twitlin.v2.tweets.TweetsApiImp
import com.sorrowblue.twitlin.v2.users.UsersApi
import com.sorrowblue.twitlin.v2.users.UsersApiImp

object TwitterAPI {

	object V2 {
		val usersApi: UsersApi by lazy { UsersApiImp(Twitlin.v2Client) }
		val tweetsApi: TweetsApi by lazy { TweetsApiImp(Twitlin.v2Client) }
	}
}
