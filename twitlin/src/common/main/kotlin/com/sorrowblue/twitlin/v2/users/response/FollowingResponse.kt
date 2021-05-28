package com.sorrowblue.twitlin.v2.users.response

import com.sorrowblue.twitlin.v2.users.Following
import kotlinx.serialization.Serializable

@Serializable
internal class FollowingResponse(val data: Following)
