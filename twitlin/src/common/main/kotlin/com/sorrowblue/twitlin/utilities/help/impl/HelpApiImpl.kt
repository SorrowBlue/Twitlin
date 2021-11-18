package com.sorrowblue.twitlin.utilities.help.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.TwitterClient
import com.sorrowblue.twitlin.utilities.help.Configuration
import com.sorrowblue.twitlin.utilities.help.HelpApi
import com.sorrowblue.twitlin.utilities.help.Language
import com.sorrowblue.twitlin.utils.API_HELP
import kotlinx.serialization.builtins.ListSerializer

internal class HelpApiImpl(private val client: TwitterClient) : HelpApi {

    override suspend fun configuration(): Response<Configuration> {
        return client.get("$API_HELP/configuration.json", Response.serializer(Configuration.serializer()))
    }

    override suspend fun languages(): Response<List<Language>> {
        return client.get("$API_HELP/languages.json", Response.serializer(ListSerializer(Language.serializer())))
    }
}
