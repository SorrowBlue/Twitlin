package com.sorrowblue.twitlin.accounts_users.account

import com.sorrowblue.twitlin.net.Client
import com.sorrowblue.twitlin.net.Response
import com.sorrowblue.twitlin.net.Urls
import com.sorrowblue.twitlin.objects.TwitterUser

private const val ACCOUNT = "${Urls._1_1}/account"

internal class AccountApiImp(private val client: Client) : AccountApi {
	override suspend fun settings(): Response<TwitterSettings> = client.get("$ACCOUNT/settings.json")

	override suspend fun verifyCredentials(
		includeEntities: Boolean,
		skipStatus: Boolean,
		includeEmail: Boolean
	): Response<TwitterUser> {
		val params = listOf(
			"include_entities" to includeEntities.toString(),
			"skip_status" to skipStatus.toString(),
			"include_email" to includeEmail.toString()
		)
		return client.get("$ACCOUNT/verify_credentials.json", params)
	}
}
