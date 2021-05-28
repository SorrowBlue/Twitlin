package com.sorrowblue.twitlin.v2.users.response

import kotlinx.serialization.Serializable

@Serializable
internal class UnFollowingResponse(val data: Data) {

    @Serializable
    class Data(val following: Boolean)
}
