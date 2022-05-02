package com.sorrowblue.twitlin.api.oauth2.response

import kotlinx.serialization.Serializable

@Serializable
internal class Oauth2InvalidateToken(val access_token: String)
