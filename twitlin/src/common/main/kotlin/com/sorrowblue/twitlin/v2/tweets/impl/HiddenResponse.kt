package com.sorrowblue.twitlin.v2.tweets.impl

import kotlinx.serialization.Serializable

@Serializable
internal class HiddenResponse(val data: Data) {

    @Serializable
    class Data(val hidden: Boolean)
}
