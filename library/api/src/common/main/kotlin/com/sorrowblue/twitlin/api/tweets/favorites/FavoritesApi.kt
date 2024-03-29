package com.sorrowblue.twitlin.api.tweets.favorites

import com.sorrowblue.twitlin.api.TwitterAPI
import com.sorrowblue.twitlin.api.client.Response
import com.sorrowblue.twitlin.api.objects.Tweet
import com.sorrowblue.twitlin.core.objects.TweetId
import com.sorrowblue.twitlin.core.objects.UserId

/**
 * TODO
 */
public interface FavoritesApi : TwitterAPI {

    /**
     * Note: favorites are now known as likes.
     *
     * Returns the 20 most recent Tweets liked by the authenticating or specified user.
     *
     * @param userId The ID of the user for whom to return results.
     * @param count Specifies the number of records to retrieve. Must be less than or equal to 200;
     * defaults to 20. The value of count is best thought of as a limit to the number of Tweets to
     * return because suspended or deleted content is removed after the count has been applied.
     * @param sinceId Returns results with an ID greater than (that is, more recent than) the
     * specified ID. There are limits to the number of Tweets which can be accessed through the API.
     * If the limit of Tweets has occured since the [sinceId],
     * the [sinceId] will be forced to the oldest ID available.
     * @param maxId Returns results with an ID less than (that is, older than) or equal to the
     * specified ID.
     * @param includeEntities The entities node will be omitted when set to `false`.
     * @return
     */
    public suspend fun list(
        userId: UserId,
        count: Int = 20,
        includeEntities: Boolean = true,
        sinceId: TweetId? = null,
        maxId: TweetId? = null
    ): Response<List<Tweet>>

    /**
     * Note: favorites are now known as likes.
     *
     * Returns the 20 most recent Tweets liked by the authenticating or specified user.
     *
     * @param screenName The screen name of the user for whom to return results.
     * @param count Specifies the number of records to retrieve. Must be less than or equal to 200;
     * defaults to 20. The value of count is best thought of as a limit to the number of Tweets to
     * return because suspended or deleted content is removed after the count has been applied.
     * @param sinceId Returns results with an ID greater than (that is, more recent than) the
     * specified ID. There are limits to the number of Tweets which can be accessed through the API.
     * If the limit of Tweets has occured since the [sinceId],
     * the [sinceId] will be forced to the oldest ID available.
     * @param maxId Returns results with an ID less than (that is, older than) or equal to the
     * specified ID.
     * @param includeEntities The entities node will be omitted when set to `false`.
     * @return
     */
    public suspend fun list(
        screenName: String,
        count: Int = 20,
        includeEntities: Boolean = true,
        sinceId: TweetId? = null,
        maxId: TweetId? = null
    ): Response<List<Tweet>>

    /**
     * Note: favorites are now known as likes.
     *
     * Favorites (likes) the Tweet specified in the ID parameter as the authenticating user.
     * Returns the favorite Tweet when successful.
     *
     * The process invoked by this method is asynchronous. The immediately returned Tweet object
     * may not indicate the resultant favorited status of the Tweet. A 200 OK response from this
     * method will indicate whether the intended action was successful or not.
     *
     * @param id The numerical ID of the Tweet to like.
     * @param includeEntities The entities node will be omitted when set to `false`.
     * @return
     */
    public suspend fun create(id: TweetId, includeEntities: Boolean = true): Response<Tweet>

    /**
     * Note: favorites are now known as likes.
     *
     * Unfavorites (un-likes) the Tweet specified in the ID parameter as the authenticating user.
     * Returns the un-liked Tweet when successful.
     *
     * The process invoked by this method is asynchronous. The immediately returned Tweet object
     * may not indicate the resultant favorited status of the Tweet. A 200 OK response from this
     * method will indicate whether the intended action was successful or not.
     *
     * @param id The numerical ID of the Tweet to un-like.
     * @param includeEntities The entities node will be omitted when set to `false`.
     * @return
     */
    public suspend fun destroy(id: TweetId, includeEntities: Boolean = true): Response<Tweet>
}
