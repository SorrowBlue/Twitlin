/*
 * (c) 2020 SorrowBlue.
 */

package com.sorrowblue.twitlin.utilities.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.TwitlinClient
import com.sorrowblue.twitlin.client.Urls
import com.sorrowblue.twitlin.utilities.Configuration
import com.sorrowblue.twitlin.utilities.HelpApi
import com.sorrowblue.twitlin.utilities.Language

private const val HELP = "${Urls.V1}/help"

internal class HelpApiImpl(private val client: TwitlinClient) : HelpApi {

    override suspend fun configuration(): Response<Configuration> =
        client.get("$HELP/configuration.json")

    override suspend fun languages(): Response<List<Language>> =
        client.get("$HELP/languages.json")
}
