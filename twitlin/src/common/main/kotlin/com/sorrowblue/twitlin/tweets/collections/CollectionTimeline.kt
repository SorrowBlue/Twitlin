package com.sorrowblue.twitlin.tweets.collections

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.objects.UserId
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@AndroidParcelize
@Serializable
public data class CollectionTimeline(
    val name: String,
    @SerialName("user_id")
    val userId: UserId,
    @SerialName("collection_url")
    val collectionUrl: String,
    @SerialName("custom_timeline_url")
    val customTimelineUrl: String,
    val description: String,
    val url: String? = null,
    val visibility: String,
    @SerialName("timeline_order")
    val timelineOrder: String,
    @SerialName("collection_type")
    val collectionType: String,
    @SerialName("custom_timeline_type")
    val customTimelineType: String
) : AndroidParcelable, JvmSerializable
