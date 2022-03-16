package com.sorrowblue.twitlin.api.v2

import com.sorrowblue.twitlin.api.v2.client.Oauth2Client
import com.sorrowblue.twitlin.api.v2.lists.ListsApi
import com.sorrowblue.twitlin.api.v2.lists.impl.ListsApiImpl
import com.sorrowblue.twitlin.api.v2.oauth2.OAuth2Api
import com.sorrowblue.twitlin.api.v2.oauth2.impl.OAuth2ApiImpl
import com.sorrowblue.twitlin.api.v2.spaces.SpacesApi
import com.sorrowblue.twitlin.api.v2.spaces.impl.SpacesApiImpl
import com.sorrowblue.twitlin.api.v2.tweets.TweetsApi
import com.sorrowblue.twitlin.api.v2.tweets.counts.TweetsCountsApi
import com.sorrowblue.twitlin.api.v2.tweets.counts.impl.TweetsCountsApiImpl
import com.sorrowblue.twitlin.api.v2.tweets.impl.TweetsApiImpl
import com.sorrowblue.twitlin.api.v2.tweets.search.TweetsSearchApi
import com.sorrowblue.twitlin.api.v2.tweets.search.impl.TweetsSearchApiImpl
import com.sorrowblue.twitlin.api.v2.users.UsersApi
import com.sorrowblue.twitlin.api.v2.users.impl.UsersApiImpl
import com.sorrowblue.twitlin.core.client.TwitterClient
import kotlin.reflect.KClass

public object TwitlinApiV2 {
    public inline fun <reified T : Any> getApi(client: TwitterClient): T {
        return getApi(client, T::class)
    }

    public fun <T : Any> getApi(client: TwitterClient, klass: KClass<T>): T {

        @Suppress("IMPLICIT_CAST_TO_ANY", "UNCHECKED_CAST")
        return when (klass) {
            // Twitter API v2
            TweetsApi::class -> TweetsApiImpl(client)
            TweetsSearchApi::class -> TweetsSearchApiImpl(client)
            TweetsCountsApi::class -> TweetsCountsApiImpl(client)
            ListsApi::class -> ListsApiImpl(client)
            SpacesApi::class -> SpacesApiImpl(client)
            UsersApi::class -> UsersApiImpl(client)
            OAuth2Api::class -> OAuth2ApiImpl(client as Oauth2Client)

            else -> throw UnsupportedOperationException("")
        } as T
    }
}
