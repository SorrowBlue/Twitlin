package com.sorrowblue.twitlin.v2.users.impl

import kotlinx.serialization.Serializable

@Serializable
internal class MutingResponse(val data: Data) {

    @Serializable
    class Data(val muting: Boolean)
}
