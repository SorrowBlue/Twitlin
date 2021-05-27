package com.sorrowblue.twitlin.v2.users.response

import kotlinx.serialization.Serializable

@Serializable
internal class LikesResponse(val data: Data) {

    @Serializable
    class Data(val liked: Boolean)
}
