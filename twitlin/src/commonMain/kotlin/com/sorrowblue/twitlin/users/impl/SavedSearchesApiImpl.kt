/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.users.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.Urls
import com.sorrowblue.twitlin.client.UserClient
import com.sorrowblue.twitlin.users.SavedSearch
import com.sorrowblue.twitlin.users.SavedSearchesApi

private const val SAVED_SEARCHES = "${Urls.V1}/saved_searches"

internal class SavedSearchesApiImpl(private val client: UserClient) : SavedSearchesApi {

    override suspend fun list(): Response<List<SavedSearch>> =
        client.get("$SAVED_SEARCHES/list.json")

    override suspend fun show(id: String): Response<SavedSearch> =
        client.get("$SAVED_SEARCHES/show/$id.json")

    override suspend fun create(query: String): Response<SavedSearch> =
        client.post("$SAVED_SEARCHES/create.json", "query" to query)

    override suspend fun destroy(id: String): Response<SavedSearch> =
        client.post("$SAVED_SEARCHES/destroy/$id.json")

}
