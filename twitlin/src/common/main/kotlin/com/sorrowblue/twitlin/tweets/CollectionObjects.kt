/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.tweets

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.objects.User
import kotlinx.serialization.Serializable

@Serializable
public sealed class CollectionObjects : JvmSerializable {

    public abstract val users: Map<String, User>
    public abstract val timelines: Map<String, CollectionTimeline>

    @AndroidParcelize
    @Serializable
    public data class Default(
        override val users: Map<String, User>,
        override val timelines: Map<String, CollectionTimeline>
    ) : CollectionObjects(), AndroidParcelable, JvmSerializable

    @AndroidParcelize
    @Serializable
    public data class HasTweets(
        val tweets: Map<String, CollectionTweet>,
        override val users: Map<String, User>,
        override val timelines: Map<String, CollectionTimeline>
    ) : CollectionObjects(), AndroidParcelable, JvmSerializable
}
