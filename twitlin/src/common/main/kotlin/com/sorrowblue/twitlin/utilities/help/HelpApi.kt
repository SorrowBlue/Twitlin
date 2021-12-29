package com.sorrowblue.twitlin.utilities.help

import com.sorrowblue.twitlin.client.Response

/**
 * The standard Twitter API supports a number of different languages. This endpoint the list of languages supported by
 * Twitter, along with the language code(s) supported by Twitter.
 */
public interface HelpApi {

    /**
     * Returns the list of languages supported by Twitter along with the language code supported
     * by Twitter.
     *
     * The language code may be formatted as ISO 639-1 alpha-2 (`en`), ISO 639-3 alpha-3 (`msa`),
     * or ISO 639-1 alpha-2 combined with an ISO 3166-1 alpha-2 localization (`zh-tw`).
     *
     * @return List of languages supported by Twitter
     */
    public suspend fun languages(): Response<List<Language>>
}
