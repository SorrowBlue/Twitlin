package com.sorrowblue.twitlin.v2.users.response

import kotlinx.serialization.Serializable

@Serializable
internal class RetweetResponse(val data: Data) {

    @Serializable
    class Data(val retweeted: Boolean)
}
