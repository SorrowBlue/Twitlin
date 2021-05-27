package com.sorrowblue.twitlin.v2.users.request

import kotlinx.serialization.Serializable

@Serializable
internal class LikesRequest(val tweet_id: String)
