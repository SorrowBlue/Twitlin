/*
 * (c) 2020 SorrowBlue.
 */

package com.sorrowblue.twitlin

import com.sorrowblue.twitlin.v2.tweets.TweetsApi
import com.sorrowblue.twitlin.v2.tweets.TweetsApiImp
import com.sorrowblue.twitlin.v2.users.UsersApi
import com.sorrowblue.twitlin.v2.users.UsersApiImp

public object TwitterV2API {
    public val tweetsApi: TweetsApi by lazy { TweetsApiImp(Twitlin.v2Client) }
    public val usersApi: UsersApi by lazy { UsersApiImp(Twitlin.v2Client) }
}
