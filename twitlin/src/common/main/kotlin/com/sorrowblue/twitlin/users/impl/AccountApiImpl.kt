/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.users.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.UserClient
import com.sorrowblue.twitlin.core.Urls
import com.sorrowblue.twitlin.objects.User
import com.sorrowblue.twitlin.users.AccountApi
import com.sorrowblue.twitlin.users.Settings
import com.sorrowblue.twitlin.utilities.LanguageCode
import kotlinx.serialization.builtins.serializer

private const val ACCOUNT = "${Urls.V1}/account"

internal class AccountApiImpl(private val client: UserClient) : AccountApi {
    override suspend fun settings(): Response<Settings> {
        return client.get("$ACCOUNT/settings.json", Response.serializer(Settings.serializer()))
    }

    override suspend fun verifyCredentials(
        includeEntities: Boolean?,
        skipStatus: Boolean?,
        includeEmail: Boolean?
    ): Response<User> {
        return client.get(
            "$ACCOUNT/verify_credentials.json",
            Response.serializer(User.serializer()),
            "include_entities" to includeEntities,
            "skip_status" to skipStatus,
            "include_email" to includeEmail
        )
    }

    override suspend fun removeProfileBanner(): Response<Unit> {
        return client.post(
            "$ACCOUNT/remove_profile_banner.json",
            Response.serializer(Unit.serializer())
        )
    }

    override suspend fun settings(
        sleepTimeEnabled: Boolean?,
        startSleepTime: Int?,
        endSleepTime: Int?,
        timeZone: String?,
        trendLocationWoeid: Int?,
        lang: LanguageCode?
    ): Response<Settings> {
        return client.post(
            "$ACCOUNT/settings.json",
            Response.serializer(Settings.serializer()),
            "sleep_time_enabled" to sleepTimeEnabled,
            "start_sleep_time" to startSleepTime,
            "end_sleep_time" to endSleepTime,
            "time_zone" to timeZone,
            "trend_location_woeid" to trendLocationWoeid,
            "lang" to lang?.value
        )
    }

    override suspend fun updateProfile(
        name: String?,
        url: String?,
        location: String?,
        description: String?,
        profileLinkColor: String?,
        includeEntities: Boolean?,
        skipStatus: Boolean?
    ): Response<User> {
        return client.post(
            "$ACCOUNT/update_profile.json",
            Response.serializer(User.serializer()),
            "name" to name,
            "url" to url,
            "location" to location,
            "description" to description,
            "profile_link_color" to profileLinkColor,
            "include_entities" to includeEntities,
            "skip_status" to skipStatus,
        )
    }

    override suspend fun updateProfileBanner(
        banner: String,
        width: Int?,
        height: Int?,
        offsetLeft: Int?,
        offsetTop: Int?
    ): Response<Unit> {
        return client.post(
            "$ACCOUNT/update_profile_banner.json",
            Response.serializer(Unit.serializer()),
            "banner" to banner,
            "width" to width,
            "height" to height,
            "offset_left" to offsetLeft,
            "offset_top" to offsetTop,
        )
    }

    override suspend fun updateProfileImage(
        image: String,
        includeEntities: Boolean?,
        skipStatus: Boolean?
    ): Response<User> {
        return client.post(
            "$ACCOUNT/update_profile_image.json",
            Response.serializer(User.serializer()),
            "image" to image,
            "include_entities" to includeEntities,
            "skip_status" to skipStatus,
        )
    }
}
