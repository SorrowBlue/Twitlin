package com.sorrowblue.twitlin

import com.sorrowblue.twitlin.authentication.OAuth2Api
import com.sorrowblue.twitlin.authentication.OAuthApi
import com.sorrowblue.twitlin.authentication.impl.OAuth2ApiImpl
import com.sorrowblue.twitlin.authentication.impl.OAuthApiImpl
import com.sorrowblue.twitlin.client.Oauth1aClient
import com.sorrowblue.twitlin.client.TwitterClient
import com.sorrowblue.twitlin.directmessages.DirectMessagesApi
import com.sorrowblue.twitlin.directmessages.WelcomeMessagesApi
import com.sorrowblue.twitlin.directmessages.impl.DirectMessagesApiImpl
import com.sorrowblue.twitlin.directmessages.impl.WelcomeMessagesApiImpl
import com.sorrowblue.twitlin.geo.GeoApi
import com.sorrowblue.twitlin.geo.impl.GeoApiImpl
import com.sorrowblue.twitlin.media.MediaApi
import com.sorrowblue.twitlin.media.impl.MediaApiImpl
import com.sorrowblue.twitlin.trends.TrendsApi
import com.sorrowblue.twitlin.trends.impl.TrendsApiImpl
import com.sorrowblue.twitlin.tweets.collections.CollectionsApi
import com.sorrowblue.twitlin.tweets.collections.impl.CollectionsApiImpl
import com.sorrowblue.twitlin.tweets.favorites.FavoritesApi
import com.sorrowblue.twitlin.tweets.favorites.impl.FavoritesApiImp
import com.sorrowblue.twitlin.tweets.search.SearchApi
import com.sorrowblue.twitlin.tweets.search.impl.SearchApiImpl
import com.sorrowblue.twitlin.tweets.statuses.StatusesApi
import com.sorrowblue.twitlin.tweets.statuses.impl.StatusesApiImp
import com.sorrowblue.twitlin.users.UsersApi
import com.sorrowblue.twitlin.users.account.AccountApi
import com.sorrowblue.twitlin.users.account.impl.AccountApiImpl
import com.sorrowblue.twitlin.users.blocks.BlocksApi
import com.sorrowblue.twitlin.users.blocks.impl.BlocksApiImpl
import com.sorrowblue.twitlin.users.followers.FollowersApi
import com.sorrowblue.twitlin.users.followers.impl.FollowersApiImpl
import com.sorrowblue.twitlin.users.friends.FriendsApi
import com.sorrowblue.twitlin.users.friends.impl.FriendsApiImpl
import com.sorrowblue.twitlin.users.friendships.FriendshipsApi
import com.sorrowblue.twitlin.users.friendships.impl.FriendshipsApiImpl
import com.sorrowblue.twitlin.users.impl.UsersApiImpl
import com.sorrowblue.twitlin.users.lists.ListsApi
import com.sorrowblue.twitlin.users.lists.impl.ListsApiImpl
import com.sorrowblue.twitlin.users.mutes.MutesApi
import com.sorrowblue.twitlin.users.mutes.impl.MutesApiImpl
import com.sorrowblue.twitlin.users.savedsearches.SavedSearchesApi
import com.sorrowblue.twitlin.users.savedsearches.impl.SavedSearchesApiImpl
import com.sorrowblue.twitlin.utilities.application.ApplicationApi
import com.sorrowblue.twitlin.utilities.application.impl.ApplicationApiImpl
import com.sorrowblue.twitlin.utilities.help.HelpApi
import com.sorrowblue.twitlin.utilities.help.impl.HelpApiImpl
import com.sorrowblue.twitlin.v2.client.Oauth2Client
import com.sorrowblue.twitlin.v2.spaces.SpacesApi
import com.sorrowblue.twitlin.v2.spaces.impl.SpacesApiImpl
import com.sorrowblue.twitlin.v2.tweets.TweetsApi
import com.sorrowblue.twitlin.v2.tweets.counts.TweetsCountsApi
import com.sorrowblue.twitlin.v2.tweets.counts.impl.TweetsCountsApiImpl
import com.sorrowblue.twitlin.v2.tweets.impl.TweetsApiImpl
import com.sorrowblue.twitlin.v2.tweets.search.TweetsSearchApi
import com.sorrowblue.twitlin.v2.tweets.search.impl.TweetsSearchApiImpl
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.SIMPLE
import kotlin.reflect.KClass
import kotlinx.datetime.TimeZone
import com.sorrowblue.twitlin.v2.lists.ListsApi as ListsApi2
import com.sorrowblue.twitlin.v2.lists.impl.ListsApiImpl as ListsApiImpl2
import com.sorrowblue.twitlin.v2.oauth2.OAuth2Api as OAuth2Api2
import com.sorrowblue.twitlin.v2.oauth2.impl.OAuth2ApiImpl as OAuth2ApiImpl2
import com.sorrowblue.twitlin.v2.users.UsersApi as UsersApi2
import com.sorrowblue.twitlin.v2.users.impl.UsersApiImpl as UsersApiImpl2

/**
 * TODO
 */
public object Twitlin {


    public var defaultTimeZone: TimeZone = TimeZone.UTC

    public var clientLogger: Logger = Logger.SIMPLE

    public var clientLogLevel: LogLevel = LogLevel.ALL

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
            ListsApi2::class -> ListsApiImpl2(client)
            SpacesApi::class -> SpacesApiImpl(client)
            UsersApi2::class -> UsersApiImpl2(client)
            OAuth2Api2::class -> OAuth2ApiImpl2(client as Oauth2Client)

            // Standard v1.1
            CollectionsApi::class -> CollectionsApiImpl(client)
            StatusesApi::class -> StatusesApiImp(client)
            FavoritesApi::class -> FavoritesApiImp(client)
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
            // Platform-wide
            OAuthApi::class -> OAuthApiImpl(client as Oauth1aClient)
            OAuth2Api::class -> OAuth2ApiImpl(client as com.sorrowblue.twitlin.client.Oauth2Client)

            else -> throw UnsupportedOperationException("")
        } as T
    }

}
