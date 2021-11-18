package com.sorrowblue.twitlin.tweets.collections

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * TODO
 */
@Serializable
public enum class TimelineOrder {

    @SerialName("curation_reverse_chron")
    CURATION_REVERSE_CHRON,

    @SerialName("tweet_chron")
    TWEET_CHRON,

    @SerialName("tweet_reverse_chron")
    TWEET_REVERSE_CHRON
}
