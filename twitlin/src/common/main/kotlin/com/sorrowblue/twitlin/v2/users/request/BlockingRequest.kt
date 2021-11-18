package com.sorrowblue.twitlin.v2.users.request

import com.sorrowblue.twitlin.objects.UserId
import kotlinx.serialization.Serializable

@Serializable
internal class BlockingRequest(val target_user_id: UserId)
