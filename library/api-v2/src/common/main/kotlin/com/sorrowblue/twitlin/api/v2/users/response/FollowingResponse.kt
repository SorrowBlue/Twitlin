package com.sorrowblue.twitlin.api.v2.users.response

import com.sorrowblue.twitlin.api.v2.users.Following
import kotlinx.serialization.Serializable

@Serializable
internal class FollowingResponse(val data: Following)
