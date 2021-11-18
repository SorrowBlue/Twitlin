package com.sorrowblue.twitlin.tweets.collections

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.objects.TweetId
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@AndroidParcelize
@Serializable
public data class CollectionChange(
    val op: Op,
    @SerialName("tweet_id")
    val tweetId: TweetId
) : AndroidParcelable, JvmSerializable {

    @Serializable
    public enum class Op {

        @SerialName("add")
        ADD,

        @SerialName("remove")
        REMOVE
    }
}
