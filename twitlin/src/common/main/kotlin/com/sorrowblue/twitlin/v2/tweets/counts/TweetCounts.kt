/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.tweets.counts

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@AndroidParcelize
@Serializable
public data class TweetCounts(val data: List<TweetCount> = emptyList(), val meta: Meta) :
    AndroidParcelable, JvmSerializable {

    @AndroidParcelize
    @Serializable
    public data class Meta(
        @SerialName("total_tweet_count") val totalTweetCount: Int,
        @SerialName("next_token") val nextToken: String? = null
    ) : AndroidParcelable, JvmSerializable
}
