package com.sorrowblue.twitlin.api.v2.users.request

import com.sorrowblue.twitlin.core.objects.TweetId
import kotlinx.serialization.Serializable

@Serializable
internal class LikesRequest(val tweet_id: TweetId)
