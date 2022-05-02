package com.sorrowblue.twitlin.api.tweets.search

/**
 * TODO
 */
public enum class ResultType {

    /**
     * Include both popular and real time results in the response.
     */
    MIXED,

    /**
     * Return only the most recent results in the response.
     */
    RECENT,

    /**
     * Return only the most popular results in the response.
     */
    POPULAR
}
