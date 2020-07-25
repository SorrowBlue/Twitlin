package com.sorrowblue.twitlin.basics.oauth2

import com.sorrowblue.twitlin.net.Response

interface OAuth2Api {
	suspend fun token(): Response<BearerToken>
	suspend fun invalidateToken()
}
