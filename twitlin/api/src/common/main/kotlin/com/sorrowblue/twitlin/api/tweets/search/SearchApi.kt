package com.sorrowblue.twitlin.api.tweets.search

import com.sorrowblue.twitlin.api.objects.Tweet
import com.sorrowblue.twitlin.core.client.Response
import com.sorrowblue.twitlin.core.objects.TweetId
import kotlinx.datetime.LocalDateTime

/**
 * TODO
 */
public interface SearchApi {

    /**
     * Returns a collection of relevant [Tweet] matching a specified query.
     *
     * Please note that Twitter's search service and, by extension,
     * the Search API is not meant to be an exhaustive source of Tweets.
     * Not all Tweets will be indexed or made available via the search interface.
     *
     * To learn how to use [Twitter Search](https://twitter.com/search) effectively, please see the
     * [Standard search operators](https://developer.twitter.com/en/docs/tweets/search/guides/standard-operators)
     * page for a list of available filter operators. Also, see the
     * [Working with Timelines](https://developer.twitter.com/en/docs/tweets/timelines/guides/working-with-timelines)
     * page to learn best practices for navigating results by [sinceId] and [maxId].
     *
     * @param q A UTF-8, URL-encoded search query of 500 characters maximum, including operators.
     * Queries may additionally be limited by complexity.
     * @param geocode Returns tweets by users located within a given radius of the given
     * latitude/longitude. The location is preferentially taking from the Geotagging API, but will
     * fall back to their Twitter profile. The parameter value is specified by
     * `listOf(latitude, longitude, radius)`, where radius units must be specified as either `"mi"`
     * (miles) or `"km"` (kilometers). Note that you cannot use the near operator via the API to
     * geocode arbitrary locations; however you can use this [geocode] parameter to search near
     * geocodes directly. A maximum of 1,000 distinct "sub-regions" will be considered when using
     * the radius modifier.
     * @param lang Restricts tweets to the given language, given by an
     * [ISO 639-1](http://en.wikipedia.org/wiki/List_of_ISO_639-1_codes) code. Language detection
     * is best-effort.
     * @param local Specify the language of the query you are sending (only `ja` is currently
     * effective). This is intended for language-specific consumers and the default should work
     * in the majority of cases.
     * @param resultType Optional. Specifies what type of search results you would prefer to
     * receive. The current default is [ResultType.MIXED]. Valid values include:
     * * [ResultType.MIXED]: Include both popular and real time results in the response.
     * * [ResultType.RECENT]: Return only the most recent results in the response.
     * * [ResultType.POPULAR]: Return only the most popular results in the response.
     * @param count The number of tweets to return per page, up to a maximum of 100. Defaults to 15. This was formerly the "rpp" parameter in the old Search API.
     * @param until Returns tweets created before the given date. Date should be formatted as YYYY-MM-DD. Keep in mind that the search index has a 7-day limit. In other words, no tweets will be found for a date older than one week.
     * @param sinceId Returns results with an ID greater than (that is, more recent than) the specified ID. There are limits to the number of Tweets which can be accessed through the API. If the limit of Tweets has occured since the since_id, the since_id will be forced to the oldest ID available.
     * @param maxId Returns results with an ID less than (that is, older than) or equal to the specified ID.
     * @param includeEntities The entities node will not be included when set to false.
     * @return
     */
    public suspend fun tweets(
        q: String,
        geocode: List<String>? = null,
        lang: String? = null,
        local: String? = null,
        resultType: ResultType = ResultType.MIXED,
        count: Int = 15,
        until: LocalDateTime? = null,
        sinceId: TweetId? = null,
        maxId: TweetId? = null,
        includeEntities: Boolean = true
    ): Response<SearchResults>
}
