/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.utilities.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.UserClient
import com.sorrowblue.twitlin.core.Urls
import com.sorrowblue.twitlin.utilities.Configuration
import com.sorrowblue.twitlin.utilities.HelpApi
import com.sorrowblue.twitlin.utilities.Language
import kotlinx.serialization.builtins.ListSerializer

private const val HELP = "${Urls.V1}/help"

internal class HelpApiImpl(private val client: UserClient) : HelpApi {

    override suspend fun configuration(): Response<Configuration> {
        return client.get(
            "$HELP/configuration.json",
            Response.serializer(Configuration.serializer())
        )
    }

    override suspend fun languages(): Response<List<Language>> {
        return client.get(
            "$HELP/languages.json",
            Response.serializer(ListSerializer(Language.serializer()))
        )
    }
}
