/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.tweets.response

import kotlinx.serialization.Serializable

@Serializable
internal class HiddenResponse(val data: Data) {

    @Serializable
    class Data(val hidden: Boolean)
}
