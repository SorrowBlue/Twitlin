/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin

import com.sorrowblue.twitlin.v2.spaces.SpacesAppApi
import com.sorrowblue.twitlin.v2.spaces.impl.SpacesAppApiImpl
import com.sorrowblue.twitlin.v2.tweets.TweetsApi
import com.sorrowblue.twitlin.v2.tweets.TweetsAppApi
import com.sorrowblue.twitlin.v2.tweets.counts.TweetsCountsAppApi
import com.sorrowblue.twitlin.v2.tweets.counts.impl.TweetsCountsAppApiImpl
import com.sorrowblue.twitlin.v2.tweets.impl.TweetsApiImp
import com.sorrowblue.twitlin.v2.tweets.impl.TweetsAppApiImpl
import com.sorrowblue.twitlin.v2.tweets.search.TweetsSearchApi
import com.sorrowblue.twitlin.v2.tweets.search.TweetsSearchAppApi
import com.sorrowblue.twitlin.v2.tweets.search.impl.TweetsSearchApiImpl
import com.sorrowblue.twitlin.v2.tweets.search.impl.TweetsSearchAppApiImpl
import com.sorrowblue.twitlin.v2.users.UsersApi
import com.sorrowblue.twitlin.v2.users.impl.UsersApiImp

public object TwitterV2API {
    public val tweetsApi: TweetsApi by lazy { TweetsApiImp(Twitlin.v2UserClient) }
    public val tweetsAppApi: TweetsAppApi by lazy { TweetsAppApiImpl(Twitlin.v2AppClient) }
    public val usersApi: UsersApi by lazy { UsersApiImp(Twitlin.v2UserClient) }

    public val tweetsSearchApi: TweetsSearchApi by lazy { TweetsSearchApiImpl(Twitlin.v2UserClient) }
    public val tweetsSearchAppApi: TweetsSearchAppApi by lazy { TweetsSearchAppApiImpl(Twitlin.v2AppClient) }

    public val tweetsCountsApi: TweetsCountsAppApi by lazy { TweetsCountsAppApiImpl(Twitlin.v2AppClient) }
    public val spacesAppApi: SpacesAppApi by lazy { SpacesAppApiImpl(Twitlin.v2AppClient) }
}
