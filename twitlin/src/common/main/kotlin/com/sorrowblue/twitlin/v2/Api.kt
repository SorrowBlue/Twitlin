package com.sorrowblue.twitlin.v2

import com.sorrowblue.twitlin.v2.client.TwitterClient
import com.sorrowblue.twitlin.v2.tweets.TweetsApi
import com.sorrowblue.twitlin.v2.tweets.impl.TweetsApiImp
import com.sorrowblue.twitlin.v2.users.UsersApi
import com.sorrowblue.twitlin.v2.users.impl.UsersApiImp
import kotlin.reflect.KClass

public fun <T : Any> getApi(client: TwitterClient, clazz: KClass<T>): T {
    if (clazz == TweetsApi::class) {
        return TweetsApiImp(client) as T
    }
    if (clazz == UsersApi::class) {
        return UsersApiImp(client) as T
    }
    throw UnsupportedOperationException("")
}
