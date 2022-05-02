package com.sorrowblue.twitlin.api.oauth.response

import kotlinx.serialization.Serializable

@Serializable
internal class OauthInvalidateToken(val access_token: String)
