package com.sorrowblue.twitlin.api.oauth

import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.annotation.JvmSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Request token
 *
 * @property oauthToken
 * @property oauthTokenSecret
 * @property oauthCallbackConfirmed
 * @constructor Create empty Request token
 */
@AndroidParcelize
@Serializable
public data class RequestToken(
    @SerialName("oauth_token")
    val oauthToken: String,
    @SerialName("oauth_token_secret")
    val oauthTokenSecret: String,
    @SerialName("oauth_callback_confirmed")
    val oauthCallbackConfirmed: Boolean
) : AndroidParcelable, JvmSerializable
