package com.sorrowblue.twitlin.utilities

import com.sorrowblue.twitlin.net.Client
import com.sorrowblue.twitlin.net.Response
import com.sorrowblue.twitlin.net.Urls
import com.sorrowblue.twitlin.objects.Language

private const val HELP = "${Urls._1_1}/help"

internal class HelpApiImp(private val client: Client) : HelpApi {
	override suspend fun configuration(): Response<Configuration> =
		client.get("$HELP/configuration.json")

	override suspend fun languages(): Response<List<Language>> = client.get("$HELP/languages.json")
}
