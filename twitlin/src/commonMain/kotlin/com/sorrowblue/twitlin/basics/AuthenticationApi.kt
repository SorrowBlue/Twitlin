package com.sorrowblue.twitlin.basics

import com.sorrowblue.twitlin.net.Response

interface AuthenticationApi {
	suspend fun authenticate(callbackUrl: String): Response<String>
	suspend fun accessToken(authenticate: Authenticate): Response<Unit>
	suspend fun oauth2Token(): Response<OAuth2Token>
}
