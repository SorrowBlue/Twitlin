package com.sorrowblue.twitlin.api.oauth2.impl

import com.sorrowblue.twitlin.api.API_OAUTH2
import com.sorrowblue.twitlin.api.client.Oauth2Client
import com.sorrowblue.twitlin.api.client.Response
import com.sorrowblue.twitlin.api.ktx.urlEncode
import com.sorrowblue.twitlin.api.oauth2.BearerToken
import com.sorrowblue.twitlin.api.oauth2.OAuth2Api
import com.sorrowblue.twitlin.api.oauth2.response.Oauth2InvalidateToken
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.http.Parameters
import io.ktor.util.encodeBase64

internal class OAuth2ApiImpl(private val client: Oauth2Client) : OAuth2Api {

    override suspend fun token(): Response<BearerToken> {
        return client.submitForm(
            "$API_OAUTH2/token",
            Response.serializer(BearerToken.serializer()),
            Parameters.build {
                append("grant_type", "client_credentials")
            }
        ) {
            val token = "${client.consumerKeys.key.urlEncode()}:${client.consumerKeys.secret.urlEncode()}"
            header(HttpHeaders.Authorization, "Basic ${token.encodeBase64()}")
        }
    }

    override suspend fun invalidateToken(): Response<String> {
        return client.submitForm(
            "$API_OAUTH2/invalidate_token",
            Response.serializer(Oauth2InvalidateToken.serializer()),
            Parameters.build {
                client.bearerToken?.let { append("access_token", it.accessToken) }
            }
        ).convertData(Oauth2InvalidateToken::access_token)
    }
}
