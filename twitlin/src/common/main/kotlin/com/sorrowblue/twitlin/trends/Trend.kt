/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.trends

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
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
    val tweetVolume: Long? = null
) : AndroidParcelable, JvmSerializable
