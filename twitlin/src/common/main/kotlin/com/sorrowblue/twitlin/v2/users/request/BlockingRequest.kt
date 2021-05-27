package com.sorrowblue.twitlin.v2.users.request

import kotlinx.serialization.Serializable

@Serializable
internal class BlockingRequest(val target_user_id: String)
