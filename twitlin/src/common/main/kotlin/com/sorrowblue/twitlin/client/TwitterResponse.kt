package com.sorrowblue.twitlin.client

/**
 * Twitter response
 *
 * @constructor Create empty Twitter response
 */
public interface TwitterResponse {

    /**
     * Rate limit info
     */
    public var rateLimitInfo: RateLimitInfo
}
