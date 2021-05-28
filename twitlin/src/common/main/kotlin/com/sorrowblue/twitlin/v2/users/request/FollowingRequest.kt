package com.sorrowblue.twitlin.v2.users.request

import kotlinx.serialization.Serializable

@Serializable
internal class FollowingRequest(val target_user_id: String)
