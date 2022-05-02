package com.sorrowblue.twitlin.api.v2.tweets.impl

import kotlinx.serialization.Serializable

@Serializable
internal class DeleteResponse(val data: Data) {

    @Serializable
    class Data(val deleted: Boolean)
}
