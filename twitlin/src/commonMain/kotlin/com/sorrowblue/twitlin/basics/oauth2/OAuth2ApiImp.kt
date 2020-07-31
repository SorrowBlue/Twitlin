package com.sorrowblue.twitlin.basics.oauth2

import com.sorrowblue.twitlin.net.Client
import com.sorrowblue.twitlin.net.Response
import com.sorrowblue.twitlin.net.Urls
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
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.parse

internal class OAuth2ApiImp(private val client: Client) : OAuth2Api {

	@OptIn(InternalAPI::class, ImplicitReflectionSerializer::class, UnstableDefault::class)
	override suspend fun token(): Response<BearerToken> {
		val bearer = "${client.apiKey.urlEncode()}:${client.apiSecretKey.urlEncode()}".encodeBase64()
		val response = client.httpClient.post<HttpResponse>("${Urls.OAUTH2}/token") {
			header(HttpHeaders.Authorization, "Basic $bearer")
			contentType(ContentType.parse("application/x-www-form-urlencoded;charset=UTF-8"))
			body = "grant_type=client_credentials"
		}
		return if (response.status.isSuccess()) {
			response.content.readUTF8Line()?.let { Json.parse<BearerToken>(it) }?.let {
				client.bearerToken = it
					Response.success(it)
			}
				?: Response.error<BearerToken>()
		} else {
			Response.error<BearerToken>()
		}
	}

	override suspend fun invalidateToken() {
		TODO("Not yet implemented")
	}
}
