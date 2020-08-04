package com.sorrowblue.twitlin.utils

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.net.ErrorMessages
import com.sorrowblue.twitlin.net.Response
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readText
import io.ktor.client.statement.request
import io.ktor.util.toMap
import io.ktor.utils.io.readUTF8Line
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

internal suspend fun <R> HttpResponse.onSuccess(parse: (String) -> R) =
	if (status.value == 200) {
		val text = readText()
		Napier.d(
			"""
			method = ${request.method}
			url = ${request.url}
			headers = ${request.headers.toMap()}
			body = $text
			""".trimIndent(), tag = "HttpResponse.onSuccess"
		)
		Response.SUCCESS(parse(text))
	} else {
		Napier.e(
			"""
			method = ${request.method}
			url = ${request.url}
			headers = ${request.headers.toMap()}
			""".trimIndent(), tag = "HttpResponse.onSuccess"
		)
		content.readUTF8Line()?.let { Json.decodeFromString<ErrorMessages>(it) }?.let { messages ->
			if (messages.errors.any { it.code == 89 }) {
				Twitlin.client.account = null
				Twitlin.onInvalidToken.invoke()
			}
			Response.Error(messages.errors)
		} ?: Response.Error(emptyList())
	}
