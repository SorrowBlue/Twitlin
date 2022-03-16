package com.sorrowblue.twitlin.api.utilities.help.impl

import com.sorrowblue.twitlin.api.utilities.help.HelpApi
import com.sorrowblue.twitlin.api.utilities.help.Language
import com.sorrowblue.twitlin.core.client.Response
import com.sorrowblue.twitlin.core.client.TwitterClient
import com.sorrowblue.twitlin.core.util.API_HELP
import kotlinx.serialization.builtins.ListSerializer

internal class HelpApiImpl(private val client: TwitterClient) : HelpApi {

    override suspend fun languages(): Response<List<Language>> {
        return client.get("$API_HELP/languages.json", Response.serializer(ListSerializer(Language.serializer())))
    }
}
