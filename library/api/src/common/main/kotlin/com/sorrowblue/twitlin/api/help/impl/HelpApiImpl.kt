package com.sorrowblue.twitlin.api.help.impl

import com.sorrowblue.twitlin.api.API_HELP
import com.sorrowblue.twitlin.api.client.Response
import com.sorrowblue.twitlin.api.help.HelpApi
import com.sorrowblue.twitlin.api.help.Language
import com.sorrowblue.twitlin.core.client.TwitterClient
import kotlinx.serialization.builtins.ListSerializer

internal class HelpApiImpl(private val client: TwitterClient) : HelpApi {

    override suspend fun languages(): Response<List<Language>> {
        return client.get("$API_HELP/languages.json", Response.serializer(ListSerializer(Language.serializer())))
    }
}
