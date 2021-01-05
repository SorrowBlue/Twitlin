/*
 * (c) 2021 SorrowBlue.
 */

/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.tweets

import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class CollectionChange(
    val op: Op,
    @SerialName("tweet_id")
    val tweetId: String
) : JvmSerializable {

    @Serializable
    public enum class Op {

        @SerialName("add")
        ADD,

        @SerialName("remove")
        REMOVE
    }
}
