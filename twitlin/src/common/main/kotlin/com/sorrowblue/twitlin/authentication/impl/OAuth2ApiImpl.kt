package com.sorrowblue.twitlin.authentication.impl

import com.sorrowblue.twitlin.authentication.BearerToken
import com.sorrowblue.twitlin.authentication.InvalidateToken
import com.sorrowblue.twitlin.authentication.OAuth2Api
import com.sorrowblue.twitlin.client.Oauth2Client
import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.utils.API_OAUTH2
import com.sorrowblue.twitlin.utils.urlEncode
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.http.Parameters
import io.ktor.util.InternalAPI
import io.ktor.util.encodeBase64

@Suppress("EXPERIMENTAL_API_USAGE_FUTURE_ERROR")
internal class OAuth2ApiImpl(private val client: Oauth2Client) : OAuth2Api {

    @OptIn(InternalAPI::class)
    override suspend fun token(): Response<BearerToken> {
        return client.submitForm(
            "$API_OAUTH2/token",
            Response.serializer(BearerToken.serializer()),
            Parameters.build { append("grant_type", "client_credentials") }) {
            val token = "${client.consumerKeys.key.urlEncode()}:${client.consumerKeys.secret.urlEncode()}"
            header(HttpHeaders.Authorization, "Basic ${token.encodeBase64()}")
        }
    }

    override suspend fun invalidateToken(): Response<InvalidateToken> {
        return client.submitForm(
            "$API_OAUTH2/invalidate_token",
            Response.serializer(InvalidateToken.serializer()),
            Parameters.build {
                client.bearerToken?.let { append("access_token", it.accessToken) }
            })
    }
}
