package com.sorrowblue.twitlin.v2.users.request

import com.sorrowblue.twitlin.objects.TweetId
import kotlinx.serialization.Serializable

@Serializable
internal class RetweetRequest(val tweet_id: TweetId)
