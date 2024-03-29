package com.sorrowblue.twitlin.api.users.savedsearches

import com.sorrowblue.twitlin.api.TwitterAPI
import com.sorrowblue.twitlin.api.client.Response

/**
 * With proper authorization your application can read and update a user's account and profile
 * settings. Not all settings are exposed via the API. See the API reference for details.
 */
public interface SavedSearchesApi : TwitterAPI {

    /**
     * Returns the authenticated user's saved search queries.
     *
     * @return
     */
    public suspend fun list(): Response<List<SavedSearch>>

    /**
     * Retrieve the information for the saved search represented by the given id. The authenticating
     * user must be the owner of saved search ID being requested.
     *
     * @param id The ID of the saved search.
     * @return
     */
    public suspend fun show(id: String): Response<SavedSearch>

    /**
     * Create a new saved search for the authenticated user. A user may only have 25 saved searches.
     *
     * @param query The query of the search the user would like to save.
     * @return
     */
    public suspend fun create(query: String): Response<SavedSearch>

    /**
     * Destroys a saved search for the authenticating user. The authenticating user must be the
     * owner of saved search id being destroyed.
     *
     * @param id The ID of the saved search.
     * @return
     */
    public suspend fun destroy(id: String): Response<SavedSearch>
}
