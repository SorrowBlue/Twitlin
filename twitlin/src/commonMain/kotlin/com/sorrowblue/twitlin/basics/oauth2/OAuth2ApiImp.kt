package com.sorrowblue.twitlin.basics.oauth2

import com.sorrowblue.twitlin.net.Client
import com.sorrowblue.twitlin.net.ErrorMessages
import com.sorrowblue.twitlin.net.Response
import com.sorrowblue.twitlin.net.Urls
import com.sorrowblue.twitlin.utils.urlEncode
import io.ktor.client.features.*
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.statement.*
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.util.InternalAPI
import io.ktor.util.encodeBase64
import io.ktor.utils.io.readUTF8Line
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

private const val OAUTH2 = "${Urls.ROOT}/oauth2"

internal class OAuth2ApiImp(private val client: Client) : OAuth2Api {
	override suspend fun token(): Response<BearerToken> =
		basicAuthorizationPost("$OAUTH2/token") { Json.decodeFromString(it) }

	override suspend fun invalidateToken(bearerToken: BearerToken): Response<String> =
		basicAuthorizationPost("$OAUTH2/invalidate_token?access_token=${bearerToken.accessToken}") {
			Json.decodeFromString<InvalidateResponse>(it).access_token
		}

	@OptIn(InternalAPI::class)
	private suspend fun <R> basicAuthorizationPost(url: String, onSuccess: (String) -> R) =
		client.httpClient.post<HttpResponse>(url) {
			val bearer =
				"${client.apiKey.urlEncode()}:${client.apiSecretKey.urlEncode()}".encodeBase64()
			header(HttpHeaders.Authorization, "Basic $bearer")
			contentType(ContentType.parse("application/x-www-form-urlencoded;charset=UTF-8"))
			body = "grant_type=client_credentials"
		}.let {
			val content = it.content.readUTF8Line() ?: return@let Response.error()
			if (it.status.isSuccess()) Response.success(onSuccess(content))
			else Response.error(Json.decodeFromString<ErrorMessages>(content))
		}

	@Serializable
	private class InvalidateResponse(val access_token: String)
}
