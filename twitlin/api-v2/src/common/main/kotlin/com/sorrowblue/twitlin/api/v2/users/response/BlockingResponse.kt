package com.sorrowblue.twitlin.api.v2.users.response

import kotlinx.serialization.Serializable

@Serializable
internal class BlockingResponse(val data: Data) {

    @Serializable
    class Data(val blocking: Boolean)
}
