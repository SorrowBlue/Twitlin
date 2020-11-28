package com.sorrowblue.twitlin

import com.sorrowblue.twitlin.basics.oauth.AccessToken
import kotlinx.serialization.Serializable

@Serializable
data class Account internal constructor(
    val id: Long,
    val profileImageUrlHttps: String,
    val name: String,
    val screenName: String,
    internal val accessToken: AccessToken
)
