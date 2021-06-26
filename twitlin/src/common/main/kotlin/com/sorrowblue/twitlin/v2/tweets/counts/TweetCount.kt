/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.tweets.counts

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.isoToLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@AndroidParcelize
@Serializable
public data class TweetCount(
    @SerialName("start") val _start: String?,
    @SerialName("end") val _end: String?,
    @SerialName("tweet_count") val tweetCount: Int
) : AndroidParcelable, JvmSerializable {

    val start: LocalDateTime? get() = _end?.isoToLocalDateTime()
    val end: LocalDateTime? get() = _end?.isoToLocalDateTime()
}
