package com.sorrowblue.twitlin.api.v2.users.request

import com.sorrowblue.twitlin.core.objects.UserId
import kotlinx.serialization.Serializable

@Serializable
internal class TargetUserIdRequest(val target_user_id: UserId)
