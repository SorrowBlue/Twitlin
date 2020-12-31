/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.utilities

import com.sorrowblue.twitlin.client.Response

/**
 * ## Get Twitter configuration details
 * A number of Twitter API parameters and defaults are fixed, including twitter.com slugs which are
 * not usernames, maximum photo resolutions, the length of t.co shortened URLs, and more.
 * Applications should request this endpoint when they are loaded, but no more than once a day to
 * check for current defaults.
 *
 * ## Get Twitter supported languages
 * The standard Twitter API supports a number of different languages. This endpoint the list of
 * languages supported by Twitter, along with the language code(s) supported by Twitter.
 */
public interface HelpApi {

    /**
     * Returns the current configuration used by Twitter including twitter.com slugs which are not
     * usernames, maximum photo resolutions, and t.co shortened URL length.
     *
     * It is recommended applications request this endpoint when they are loaded,
     * but no more than once a day.
     *
     * @return TODO
     */
    public suspend fun configuration(): Response<Configuration>

    /**
     * Returns the list of languages supported by Twitter along with the language code supported
     * by Twitter.
     *
     * The language code may be formatted as ISO 639-1 alpha-2 (`en`), ISO 639-3 alpha-3 (`msa`),
     * or ISO 639-1 alpha-2 combined with an ISO 3166-1 alpha-2 localization (`zh-tw`).
     *
     * @return TODO
     */
    public suspend fun languages(): Response<List<Language>>
}
