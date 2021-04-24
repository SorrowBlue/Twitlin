/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.tweets

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@AndroidParcelize
@Serializable
public data class CollectionChange(
    val op: Op,
    @SerialName("tweet_id")
    val tweetId: String
) : AndroidParcelable, JvmSerializable {

    @Serializable
    public enum class Op {

        @SerialName("add")
        ADD,

        @SerialName("remove")
        REMOVE
    }
}
