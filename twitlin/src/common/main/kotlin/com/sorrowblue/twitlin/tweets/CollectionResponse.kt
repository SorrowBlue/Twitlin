/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.tweets

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.collections.List as KList

@Serializable
public sealed class CollectionResponse : JvmSerializable {

    @AndroidParcelize
    @Serializable
    public data class List(val results: KList<Result>, val cursors: Cursor) :
        CollectionResponse(), AndroidParcelable, JvmSerializable

    @AndroidParcelize
    @Serializable
    public data class TimelinePosition(
        val timeline: KList<Timeline>,
        @SerialName("timeline_id")
        val timelineId: String,
        val position: Position,
    ) : CollectionResponse(), AndroidParcelable, JvmSerializable

    @AndroidParcelize
    @Serializable
    public data class TimelineId(
        @SerialName("timeline_id")
        val value: String
    ) : CollectionResponse(), AndroidParcelable, JvmSerializable

    @AndroidParcelize
    @Serializable
    public data class Errors(
        val errors: KList<Error>
    ) : CollectionResponse(), AndroidParcelable, JvmSerializable {

        @AndroidParcelize
        @Serializable
        public data class Error(
            val reason: String,
            val change: CollectionChange
        ) : AndroidParcelable, JvmSerializable
    }

    @AndroidParcelize
    @Serializable
    public data class Result(
        @SerialName("timeline_id")
        val timelineId: String
    ) : AndroidParcelable, JvmSerializable

    @AndroidParcelize
    @Serializable
    public data class Cursor(
        @SerialName("next_cursor")
        val nextCursor: String
    ) : AndroidParcelable, JvmSerializable

    @AndroidParcelize
    @Serializable
    public data class Timeline(
        val tweet: Tweet,
        @SerialName("feature_context")
        val featureContext: String,
    ) : AndroidParcelable, JvmSerializable {

        @AndroidParcelize
        @Serializable
        public data class Tweet(
            val id: String,
            @SerialName("sort_index")
            val sortIndex: String
        ) : AndroidParcelable, JvmSerializable
    }

    @AndroidParcelize
    @Serializable
    public data class Position(
        @SerialName("max_position")
        val maxPosition: String,
        @SerialName("min_position")
        val minPosition: String,
        @SerialName("was_truncated")
        val wasTruncated: Boolean
    ) : AndroidParcelable, JvmSerializable
}
