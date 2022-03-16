package com.sorrowblue.twitlin.api.users.account.impl

import com.sorrowblue.twitlin.api.objects.User
import com.sorrowblue.twitlin.api.users.account.AccountApi
import com.sorrowblue.twitlin.api.users.account.Settings
import com.sorrowblue.twitlin.core.client.Response
import com.sorrowblue.twitlin.core.client.TwitterClient
import com.sorrowblue.twitlin.core.util.API_ACCOUNT
import io.ktor.client.request.parameter
import io.ktor.http.Parameters
import kotlinx.serialization.builtins.serializer

@Suppress("EXPERIMENTAL_API_USAGE_FUTURE_ERROR")
internal class AccountApiImpl(private val client: TwitterClient) : AccountApi {

    override suspend fun settings(): Response<Settings> {
        return client.get("$API_ACCOUNT/settings.json", Response.serializer(Settings.serializer()))
    }

    override suspend fun verifyCredentials(
        includeEntities: Boolean,
        skipStatus: Boolean,
        includeEmail: Boolean
    ): Response<User> {
        return client.get("$API_ACCOUNT/verify_credentials.json", Response.serializer(User.serializer())) {
            parameter("include_entities", includeEntities)
            parameter("skip_status", skipStatus)
            parameter("include_email", includeEmail)
        }
    }

    override suspend fun removeProfileBanner(): Response<Unit> {
        return client.post("$API_ACCOUNT/remove_profile_banner.json", Response.serializer(Unit.serializer()))
    }

    override suspend fun settings(
        sleepTimeEnabled: Boolean?,
        startSleepTime: Int?,
        endSleepTime: Int?,
        timeZone: String?,
        trendLocationWoeid: Int?,
        lang: String?
    ): Response<Settings> {
        return client.submitForm(
            "$API_ACCOUNT/settings.json",
            Response.serializer(Settings.serializer()),
            Parameters.build {
                sleepTimeEnabled?.let { append("sleep_time_enabled", it.toString()) }
                startSleepTime?.let { append("start_sleep_time", it.toString()) }
                endSleepTime?.let { append("end_sleep_time", it.toString()) }
                timeZone?.let { append("time_zone", it) }
                trendLocationWoeid?.let { append("trend_location_woeid", it.toString()) }
                lang?.let { append("lang", it) }
            }
        )
    }

    override suspend fun updateProfile(
        name: String?,
        url: String?,
        location: String?,
        description: String?,
        profileLinkColor: String?,
        includeEntities: Boolean,
        skipStatus: Boolean
    ): Response<User> {
        return client.submitForm(
            "$API_ACCOUNT/update_profile.json",
            Response.serializer(User.serializer()),
            Parameters.build {
                name?.let { append("name", it) }
                url?.let { append("url", it) }
                location?.let { append("location", it) }
                description?.let { append("description", it) }
                profileLinkColor?.let { append("profile_link_color", it) }
                append("include_entities", includeEntities.toString())
                append("skip_status", skipStatus.toString())
            }
        )
    }

    override suspend fun updateProfileBanner(
        banner: String,
        width: Int?,
        height: Int?,
        offsetLeft: Int?,
        offsetTop: Int?
    ): Response<Unit> {
        return client.submitForm(
            "$API_ACCOUNT/update_profile_banner.json",
            Response.serializer(Unit.serializer()),
            Parameters.build {
                append("banner", banner)
                width?.let { append("width", it.toString()) }
                height?.let { append("height", it.toString()) }
                offsetLeft?.let { append("offset_left", it.toString()) }
                offsetTop?.let { append("offset_top", it.toString()) }
            }
        )
    }

    override suspend fun updateProfileImage(
        image: String,
        includeEntities: Boolean,
        skipStatus: Boolean
    ): Response<User> {
        return client.submitForm(
            "$API_ACCOUNT/update_profile_image.json",
            Response.serializer(User.serializer()),
            Parameters.build {
                append("image", image)
                append("include_entities", includeEntities.toString())
                append("skip_status", skipStatus.toString())
            }
        )
    }
}
