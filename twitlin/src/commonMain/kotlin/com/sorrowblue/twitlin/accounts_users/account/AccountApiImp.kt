package com.sorrowblue.twitlin.accounts_users.account

import com.sorrowblue.twitlin.net.Client
import com.sorrowblue.twitlin.net.Response
import com.sorrowblue.twitlin.net.Urls
import com.sorrowblue.twitlin.objects.TwitterUser

private const val ACCOUNT = "${Urls._1_1}/account"

internal class AccountApiImp(private val client: Client) : AccountApi {
	override suspend fun verifyCredentials(
		includeEntities: Boolean?,
		skipStatus: Boolean?,
		includeEmail: Boolean?
	): Response<TwitterUser> = client.get(
		"$ACCOUNT/verify_credentials.json",
		"include_entities" to includeEntities,
		"skip_status" to skipStatus,
		"include_email" to includeEmail
	)

	override suspend fun removeProfileBanner(): Response<Unit> =
		client.post("$ACCOUNT/remove_profile_banner.json")

	override suspend fun settings(
		sleepTime: TwitterSettings.SleepTime?,
		timeZone: String?,
		trendLocationWoeid: Int?,
		lang: String?
	): Response<TwitterSettings> =
		if (sleepTime == null && timeZone == null && trendLocationWoeid == null && lang == null) {
			client.get("$ACCOUNT/settings.json")
		} else {
			client.get("$ACCOUNT/settings.json",
			"sleep_time_enabled" to sleepTime?.enabled,
			"start_sleep_time" to sleepTime?.startTime,
			"end_sleep_time" to sleepTime?.endTime,
			"time_zone" to timeZone,
			"trend_location_woeid" to trendLocationWoeid,
			"lang" to lang)
		}

	override suspend fun updateProfile(
		name: String?,
		url: String?,
		location: String?,
		description: String?,
		includeEntities: Boolean,
		skipStatus: Boolean
	): Response<TwitterUser> {
		TODO("Not yet implemented")
	}

	override suspend fun updateProfileBanner(banner: String): Response<Unit> {
		TODO("Not yet implemented")
	}

	override suspend fun updateProfileBanner(
		banner: String,
		width: Int,
		height: Int,
		offsetLeft: Int,
		offsetTop: Int
	): Response<Unit> {
		TODO("Not yet implemented")
	}

	override suspend fun updateProfileImage(
		image: String,
		includeEntities: Boolean,
		skipStatus: Boolean
	): Response<TwitterUser> {
		TODO("Not yet implemented")
	}
}
