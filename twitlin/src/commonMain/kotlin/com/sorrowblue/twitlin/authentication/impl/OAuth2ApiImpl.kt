/*
 * (c) 2020 SorrowBlue.
 */

package com.sorrowblue.twitlin.authentication.impl

import com.sorrowblue.twitlin.authentication.BearerToken
import com.sorrowblue.twitlin.authentication.InvalidateToken
import com.sorrowblue.twitlin.authentication.OAuth2Api
import com.sorrowblue.twitlin.client.ErrorMessages
import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.TwitlinClient
import com.sorrowblue.twitlin.client.Urls
import com.sorrowblue.twitlin.utils.urlEncode
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.util.InternalAPI
import io.ktor.util.encodeBase64
import io.ktor.utils.io.readUTF8Line
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

private const val OAUTH2 = "${Urls.FQDN}/oauth2"

internal class OAuth2ApiImpl(private val client: TwitlinClient) : OAuth2Api {
    override suspend fun token(): Response<BearerToken> =
        post("$OAUTH2/token", Json.Default::decodeFromString)

    override suspend fun invalidateToken(): Response<InvalidateToken> = post(
        "$OAUTH2/invalidate_token?access_token=${client.bearerToken?.accessToken}",
        Json.Default::decodeFromString
    )

    @OptIn(InternalAPI::class)
    private suspend fun <T> post(url: String, onSuccess: (String) -> T) =
        client.httpClient.post<HttpResponse>(url) {
            val bearer =
                "${client.apiKey.urlEncode()}:${client.secretKey.urlEncode()}".encodeBase64()
            header(HttpHeaders.Authorization, "Basic $bearer")
            contentType(ContentType.parse("application/x-www-form-urlencoded;charset=UTF-8"))
            body = "grant_type=client_credentials"
        }.let {
            val content = it.content.readUTF8Line() ?: return@let Response.Error(
                ErrorMessages(
                    listOf(ErrorMessages.Error("", 100))
                )
            )
            if (it.status.isSuccess()) Response.Success(onSuccess(content), it.status.value)
            else Response.Error(Json.decodeFromString(content))
        }

}
