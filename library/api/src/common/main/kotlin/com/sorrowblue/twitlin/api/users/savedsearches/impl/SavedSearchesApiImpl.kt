package com.sorrowblue.twitlin.api.users.savedsearches.impl

import com.sorrowblue.twitlin.api.users.savedsearches.SavedSearch
import com.sorrowblue.twitlin.api.users.savedsearches.SavedSearchesApi
import com.sorrowblue.twitlin.api.client.Response
import com.sorrowblue.twitlin.core.client.TwitterClient
import com.sorrowblue.twitlin.api.API_SAVED_SEARCHES
import io.ktor.http.Parameters
import kotlinx.serialization.builtins.ListSerializer

@Suppress("EXPERIMENTAL_API_USAGE_FUTURE_ERROR")
internal class SavedSearchesApiImpl(private val client: TwitterClient) : SavedSearchesApi {

    override suspend fun list(): Response<List<SavedSearch>> {
        return client.get("$API_SAVED_SEARCHES/list.json", Response.serializer(ListSerializer(SavedSearch.serializer())))
    }

    override suspend fun show(id: String): Response<SavedSearch> {
        return client.get("$API_SAVED_SEARCHES/show/$id.json", Response.serializer(SavedSearch.serializer()))
    }

    override suspend fun create(query: String): Response<SavedSearch> {
        return client.submitForm(
            "$API_SAVED_SEARCHES/create.json",
            Response.serializer(SavedSearch.serializer()),
            Parameters.build { append("query", query) }
        )
    }

    override suspend fun destroy(id: String): Response<SavedSearch> {
        return client.post("$API_SAVED_SEARCHES/destroy/$id.json", Response.serializer(SavedSearch.serializer()))
    }
}
