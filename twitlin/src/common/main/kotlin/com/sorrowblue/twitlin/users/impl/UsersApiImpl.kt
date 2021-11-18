package com.sorrowblue.twitlin.users.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.TwitterClient
import com.sorrowblue.twitlin.objects.User
import com.sorrowblue.twitlin.objects.UserId
import com.sorrowblue.twitlin.objects.toParameter
import com.sorrowblue.twitlin.users.ProfileBanner
import com.sorrowblue.twitlin.users.UsersApi
import com.sorrowblue.twitlin.utils.API_USERS
import io.ktor.client.request.parameter
import kotlinx.serialization.builtins.ListSerializer

internal class UsersApiImpl(private val client: TwitterClient) : UsersApi {

    override suspend fun lookup(
        userId: List<UserId>,
        includeEntities: Boolean,
        tweetMode: Boolean?
    ): Response<List<User>> {
        return lookupImpl(userId, null, includeEntities, tweetMode)
    }

    override suspend fun lookupByScreenName(
        screenName: List<String>,
        includeEntities: Boolean,
        tweetMode: Boolean?
    ): Response<List<User>> {
        return lookupImpl(null, screenName, includeEntities, tweetMode)
    }

    private suspend fun lookupImpl(
        userId: List<UserId>?,
        screenName: List<String>?,
        includeEntities: Boolean,
        tweetMode: Boolean?
    ): Response<List<User>> {
        return client.get("$API_USERS/lookup.json", Response.serializer(ListSerializer(User.serializer()))) {
            parameter("user_id", userId?.toParameter())
            parameter("screen_name", screenName?.joinToString(","))
            parameter("include_entities", includeEntities)
            parameter("tweet_mode", tweetMode)
        }
    }

    override suspend fun search(q: String, page: Int, count: Int, includeEntities: Boolean): Response<List<User>> {
        return client.get("$API_USERS/search.json", Response.serializer(ListSerializer(User.serializer()))) {
            parameter("q", q)
            parameter("page", count)
            parameter("count", includeEntities)
            parameter("include_entities", includeEntities)
        }
    }

    override suspend fun show(userId: UserId, includeEntities: Boolean): Response<User> =
        show(userId, null, includeEntities)

    override suspend fun show(screenName: String, includeEntities: Boolean): Response<User> =
        show(null, screenName, includeEntities)

    private suspend fun show(userId: UserId?, screenName: String?, includeEntities: Boolean): Response<User> {
        return client.get("$API_USERS/show.json", Response.serializer(User.serializer())) {
            parameter("user_id", userId?.id)
            parameter("screen_name", screenName)
            parameter("include_entities", includeEntities)
        }
    }

    override suspend fun profileBanner(userId: UserId): Response<ProfileBanner> = profileBanner(userId, null)

    override suspend fun profileBanner(screenName: String): Response<ProfileBanner> = profileBanner(null, screenName)

    private suspend fun profileBanner(userId: UserId?, screenName: String?): Response<ProfileBanner> {
        return client.get("$API_USERS/profile_banner.json", Response.serializer(ProfileBanner.serializer())) {
            parameter("user_id", userId?.id)
            parameter("screen_name", screenName)
        }
    }

    override suspend fun reportSpam(userId: UserId, performBlock: Boolean): Response<User> =
        reportSpam(userId, null, performBlock)

    override suspend fun reportSpam(screenName: String, performBlock: Boolean): Response<User> =
        reportSpam(null, screenName, performBlock)

    private suspend fun reportSpam(userId: UserId?, screenName: String?, performBlock: Boolean): Response<User> {
        return client.get("$API_USERS/report_spam.json", Response.serializer(User.serializer())) {
            parameter("user_id", userId?.id)
            parameter("screen_name", screenName)
            parameter("perform_block", performBlock)
        }
    }
}
