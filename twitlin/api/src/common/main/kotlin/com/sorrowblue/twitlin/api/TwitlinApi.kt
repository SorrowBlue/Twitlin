package com.sorrowblue.twitlin.api

import com.sorrowblue.twitlin.api.directmessages.DirectMessagesApi
import com.sorrowblue.twitlin.api.directmessages.WelcomeMessagesApi
import com.sorrowblue.twitlin.api.directmessages.impl.DirectMessagesApiImpl
import com.sorrowblue.twitlin.api.directmessages.impl.WelcomeMessagesApiImpl
import com.sorrowblue.twitlin.api.geo.GeoApi
import com.sorrowblue.twitlin.api.geo.impl.GeoApiImpl
import com.sorrowblue.twitlin.api.media.MediaApi
import com.sorrowblue.twitlin.api.media.impl.MediaApiImpl
import com.sorrowblue.twitlin.api.trends.TrendsApi
import com.sorrowblue.twitlin.api.trends.impl.TrendsApiImpl
import com.sorrowblue.twitlin.api.tweets.collections.CollectionsApi
import com.sorrowblue.twitlin.api.tweets.collections.impl.CollectionsApiImpl
import com.sorrowblue.twitlin.api.tweets.favorites.FavoritesApi
import com.sorrowblue.twitlin.api.tweets.favorites.impl.FavoritesApiImpl
import com.sorrowblue.twitlin.api.tweets.search.SearchApi
import com.sorrowblue.twitlin.api.tweets.search.impl.SearchApiImpl
import com.sorrowblue.twitlin.api.tweets.statuses.StatusesApi
import com.sorrowblue.twitlin.api.tweets.statuses.impl.StatusesApiImp
import com.sorrowblue.twitlin.api.users.UsersApi
import com.sorrowblue.twitlin.api.users.account.AccountApi
import com.sorrowblue.twitlin.api.users.account.impl.AccountApiImpl
import com.sorrowblue.twitlin.api.users.blocks.BlocksApi
import com.sorrowblue.twitlin.api.users.blocks.impl.BlocksApiImpl
import com.sorrowblue.twitlin.api.users.followers.FollowersApi
import com.sorrowblue.twitlin.api.users.followers.impl.FollowersApiImpl
import com.sorrowblue.twitlin.api.users.friends.FriendsApi
import com.sorrowblue.twitlin.api.users.friends.impl.FriendsApiImpl
import com.sorrowblue.twitlin.api.users.friendships.FriendshipsApi
import com.sorrowblue.twitlin.api.users.friendships.impl.FriendshipsApiImpl
import com.sorrowblue.twitlin.api.users.impl.UsersApiImpl
import com.sorrowblue.twitlin.api.users.lists.ListsApi
import com.sorrowblue.twitlin.api.users.lists.impl.ListsApiImpl
import com.sorrowblue.twitlin.api.users.mutes.MutesApi
import com.sorrowblue.twitlin.api.users.mutes.impl.MutesApiImpl
import com.sorrowblue.twitlin.api.users.savedsearches.SavedSearchesApi
import com.sorrowblue.twitlin.api.users.savedsearches.impl.SavedSearchesApiImpl
import com.sorrowblue.twitlin.api.utilities.application.ApplicationApi
import com.sorrowblue.twitlin.api.utilities.application.impl.ApplicationApiImpl
import com.sorrowblue.twitlin.api.utilities.help.HelpApi
import com.sorrowblue.twitlin.api.utilities.help.impl.HelpApiImpl
import com.sorrowblue.twitlin.core.client.TwitterClient
import kotlin.reflect.KClass

public object TwitlinApi {
    public inline fun <reified T : Any> getApi(client: TwitterClient): T {
        return getApi(client, T::class)
    }

    public fun <T : Any> getApi(client: TwitterClient, klass: KClass<T>): T {

        @Suppress("IMPLICIT_CAST_TO_ANY", "UNCHECKED_CAST")
        return when (klass) {
            // Standard v1.1
            CollectionsApi::class -> CollectionsApiImpl(client)
            StatusesApi::class -> StatusesApiImp(client)
            FavoritesApi::class -> FavoritesApiImpl(client)
            SearchApi::class -> SearchApiImpl(client)
            ListsApi::class -> ListsApiImpl(client)
            FollowersApi::class -> FollowersApiImpl(client)
            FriendsApi::class -> FriendsApiImpl(client)
            FriendshipsApi::class -> FriendshipsApiImpl(client)
            UsersApi::class -> UsersApiImpl(client)
            AccountApi::class -> AccountApiImpl(client)
            SavedSearchesApi::class -> SavedSearchesApiImpl(client)
            BlocksApi::class -> BlocksApiImpl(client)
            MutesApi::class -> MutesApiImpl(client)
            DirectMessagesApi::class -> DirectMessagesApiImpl(client)
            WelcomeMessagesApi::class -> WelcomeMessagesApiImpl(client)
            MediaApi::class -> MediaApiImpl(client)
            TrendsApi::class -> TrendsApiImpl(client)
            GeoApi::class -> GeoApiImpl(client)
            HelpApi::class -> HelpApiImpl(client)
            ApplicationApi::class -> ApplicationApiImpl(client)
            else -> throw UnsupportedOperationException("")
        } as T
    }
}
