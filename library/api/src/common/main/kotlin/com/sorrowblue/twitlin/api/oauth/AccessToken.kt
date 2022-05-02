package com.sorrowblue.twitlin.api.oauth

import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.annotation.JvmSerializable
import com.sorrowblue.twitlin.core.objects.UserId
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Access token
 *
 * @property oauthToken
 * @property oauthTokenSecret
 * @property userId
 * @property screenName
 */
@AndroidParcelize
@Serializable
public data class AccessToken(
    @SerialName("oauth_token")
    val oauthToken: String,
    @SerialName("oauth_token_secret")
    val oauthTokenSecret: String,
    @SerialName("user_id")
    val userId: UserId,
    @SerialName("screen_name")
    val screenName: String
) : AndroidParcelable, JvmSerializable
