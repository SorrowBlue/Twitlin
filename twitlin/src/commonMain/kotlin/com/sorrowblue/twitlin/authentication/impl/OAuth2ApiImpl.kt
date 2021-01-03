/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.authentication.impl

import com.sorrowblue.twitlin.authentication.BearerToken
import com.sorrowblue.twitlin.authentication.InvalidateToken
import com.sorrowblue.twitlin.authentication.OAuth2Api
import com.sorrowblue.twitlin.client.AppClient
import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.Urls

private const val OAUTH2 = "${Urls.FQDN}/oauth2"

internal class OAuth2ApiImpl(private val client: AppClient) : OAuth2Api {

    override suspend fun token(): Response<BearerToken> =
        client.postForClientCredentials("$OAUTH2/token")

    override suspend fun invalidateToken(): Response<InvalidateToken> =
        client.post("$OAUTH2/invalidate_token", "access_token" to client.bearerToken?.accessToken)
}
