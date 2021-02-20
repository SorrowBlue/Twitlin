/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.users.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.UserClient
import com.sorrowblue.twitlin.core.Urls
import com.sorrowblue.twitlin.users.SavedSearch
import com.sorrowblue.twitlin.users.SavedSearchesApi
import kotlinx.serialization.builtins.ListSerializer

private const val SAVED_SEARCHES = "${Urls.V1}/saved_searches"

internal class SavedSearchesApiImpl(private val client: UserClient) : SavedSearchesApi {
    override suspend fun list(): Response<List<SavedSearch>> {
        return client.get(
            "$SAVED_SEARCHES/list.json",
            Response.serializer(ListSerializer(SavedSearch.serializer()))
        )
    }

    override suspend fun show(id: String): Response<SavedSearch> {
        return client.get(
            "$SAVED_SEARCHES/show/$id.json",
            Response.serializer(SavedSearch.serializer())
        )
    }

    override suspend fun create(query: String): Response<SavedSearch> {
        return client.post(
            "$SAVED_SEARCHES/create.json",
            Response.serializer(SavedSearch.serializer()),
            "query" to query
        )
    }

    override suspend fun destroy(id: String): Response<SavedSearch> {
        return client.post(
            "$SAVED_SEARCHES/destroy/$id.json",
            Response.serializer(SavedSearch.serializer())
        )
    }
}
