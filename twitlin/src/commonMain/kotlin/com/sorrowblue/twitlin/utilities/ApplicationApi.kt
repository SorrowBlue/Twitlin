/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.utilities

import com.sorrowblue.twitlin.client.Response

/**
 * This endpoint is only accurate for standard APIs
 *
 * This endpoint enables a subset of the the current rate limits for read-only (GET) operations to
 * be programmatically queried. Every application is allocated a set of rate limits.
 */
public interface ApplicationApi {

    /**
     * Returns the current rate limits for methods belonging to the specified resource families.
     *
     * Each API resource belongs to a "resource family" which is indicated in its method
     * documentation. The method's resource family can be determined from the first component
     * of the path after the resource version.
     *
     * This method responds with a map of methods belonging to the families specified by the
     * [resourceFamily] parameter, the current remaining uses for each of those resources within
     * the current rate limiting window, and their expiration time in
     * [epoch time](http://en.wikipedia.org/wiki/Unix_time). It also includes a
     * [RateLimitStatus.rateLimitContext] field that indicates the current access token or
     * application-only authentication context.
     *
     * You may also issue requests to this method without any parameters to receive a map of all
     * rate limited GET methods. If your application only uses a few of methods, you should
     * explicitly provide a [resourceFamily] parameter with the specified resource families you work with.
     *
     * When using application-only auth, this method's response indicates the application-only
     * auth rate limiting context.
     *
     * Read more about
     * [API Rate Limiting](https://developer.twitter.com/en/docs/basics/rate-limiting) and
     * [review the limits](https://developer.twitter.com/en/docs/basics/rate-limits).
     *
     * @param resourceFamily A comma-separated list of resource families you want to know the
     * current rate limit disposition for. For best performance, only specify the resource families
     * pertinent to your application. See
     * [API Rate Limiting](https://developer.twitter.com/en/docs/basics/rate-limiting)
     * for more information.
     * @return TODO
     */
    public suspend fun rateLimitStatus(resourceFamily: List<ResourceFamily> = emptyList()): Response<RateLimitStatus>
}
