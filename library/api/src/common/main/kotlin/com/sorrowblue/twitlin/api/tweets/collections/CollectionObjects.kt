package com.sorrowblue.twitlin.api.tweets.collections

import com.sorrowblue.twitlin.api.objects.User
import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.annotation.JvmSerializable
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
