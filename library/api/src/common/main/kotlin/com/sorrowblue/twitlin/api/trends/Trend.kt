package com.sorrowblue.twitlin.api.trends

import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.annotation.JvmSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@AndroidParcelize
@Serializable
public data class Trend(
    val name: String,
    val url: String,
    @SerialName("promoted_content")
    val promotedContent: Boolean?,
    val query: String,
    @SerialName("tweet_volume")
    val tweetVolume: Long? = null
) : AndroidParcelable, JvmSerializable
