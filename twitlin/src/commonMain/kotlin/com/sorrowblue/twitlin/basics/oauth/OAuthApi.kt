package com.sorrowblue.twitlin.basics.oauth

import com.sorrowblue.twitlin.basics.Authenticate
import com.sorrowblue.twitlin.net.Response

interface OAuthApi {
	suspend fun accessToken(authenticate: Authenticate): Response<Unit>
	suspend fun authenticate(callbackUrl: String): Response<String>
	suspend fun authorize(forceLogin: Boolean = false, screenName: String = ""): Response<String>
	suspend fun invalidateToken()

}
