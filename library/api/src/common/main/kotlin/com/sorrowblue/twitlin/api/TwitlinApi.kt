package com.sorrowblue.twitlin.api

import com.sorrowblue.twitlin.api.application.ApplicationApi
import com.sorrowblue.twitlin.api.application.impl.ApplicationApiImpl
import com.sorrowblue.twitlin.api.client.ConsumerKeys
import com.sorrowblue.twitlin.api.client.Oauth1aClient
import com.sorrowblue.twitlin.api.client.Oauth2Client
import com.sorrowblue.twitlin.api.client.calculateSignatureBase64
import com.sorrowblue.twitlin.api.client.creatingSignatureBaseString
import com.sorrowblue.twitlin.api.client.gettingSigningKey
import com.sorrowblue.twitlin.api.directmessages.DirectMessagesApi
import com.sorrowblue.twitlin.api.directmessages.WelcomeMessagesApi
import com.sorrowblue.twitlin.api.directmessages.impl.DirectMessagesApiImpl
import com.sorrowblue.twitlin.api.directmessages.impl.WelcomeMessagesApiImpl
import com.sorrowblue.twitlin.api.geo.GeoApi
import com.sorrowblue.twitlin.api.geo.impl.GeoApiImpl
import com.sorrowblue.twitlin.api.help.HelpApi
import com.sorrowblue.twitlin.api.help.impl.HelpApiImpl
import com.sorrowblue.twitlin.api.ktx.buildHeaderString
import com.sorrowblue.twitlin.api.ktx.collectingParameters
import com.sorrowblue.twitlin.api.media.MediaApi
import com.sorrowblue.twitlin.api.media.impl.MediaApiImpl
import com.sorrowblue.twitlin.api.oauth.AccessToken
import com.sorrowblue.twitlin.api.oauth.OAuthApi
import com.sorrowblue.twitlin.api.oauth.impl.OAuthApiImpl
import com.sorrowblue.twitlin.api.oauth2.OAuth2Api
import com.sorrowblue.twitlin.api.oauth2.impl.OAuth2ApiImpl
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
import com.sorrowblue.twitlin.core.client.TwitterClient
import io.ktor.http.HttpMethod
import io.ktor.util.encodeBase64
import kotlin.random.Random
import kotlin.reflect.KClass
import kotlinx.datetime.Clock

internal fun createSignature(
    url: String,
    httpMethod: HttpMethod,
    consumerKeys: ConsumerKeys,
    nonce: String,
    timestamp: String,
    accessToken: AccessToken
): String {
    val parameterString = com.sorrowblue.twitlin.api.client.collectingParameters(consumerKeys.key, nonce, timestamp, accessToken.oauthToken, emptyList())
    val baseString = creatingSignatureBaseString(httpMethod.value, url, parameterString)
    val signingKey = gettingSigningKey(consumerKeys.secret, accessToken.oauthTokenSecret)
    return calculateSignatureBase64(baseString, signingKey)
}

public object TwitlinApi {

    public fun authorizationHeader(url: String, consumerKeys: ConsumerKeys, accessToken: AccessToken): String {
        val nc = Random.nextBytes(32).encodeBase64().trim('=')
        val ts = Clock.System.now().epochSeconds.toString()
        val sign = createSignature(url, HttpMethod.Get, consumerKeys, nc, ts, accessToken)
        val parameters =
            collectingParameters(consumerKeys.key, nc, sign, ts, accessToken.oauthToken, emptyList())
        return buildHeaderString(parameters)
    }

    public inline fun <reified T : TwitterAPI> getApi(client: TwitterClient): T {
        return getApi(client, T::class)
    }

    @Suppress("UNCHECKED_CAST")
    public fun <T : TwitterAPI> getApi(client: TwitterClient, klass: KClass<T>): T {
        return when (klass) {
            OAuthApi::class -> {
                require(client is Oauth1aClient)
                OAuthApiImpl(client)
            }
            OAuth2Api::class -> {
                require(client is Oauth2Client)
                OAuth2ApiImpl(client)
            }
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
