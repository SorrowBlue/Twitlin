package com.sorrowblue.twitlin.v2.tweets.counts

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.annotation.KotlinIgnoredOnParcel
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.isoToLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@AndroidParcelize
@Serializable
public data class TweetCount(
    @SerialName("start") val _start: String?,
    @SerialName("end") val _end: String?,
    @SerialName("tweet_count") val tweetCount: Int
) : AndroidParcelable, JvmSerializable {

    @KotlinIgnoredOnParcel
    @Transient
    val start: LocalDateTime? = _end?.isoToLocalDateTime()

    @KotlinIgnoredOnParcel
    @Transient
    val end: LocalDateTime? = _end?.isoToLocalDateTime()
}
