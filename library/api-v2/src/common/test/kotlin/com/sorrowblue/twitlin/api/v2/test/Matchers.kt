package com.sorrowblue.twitlin.api.v2.test

import com.sorrowblue.twitlin.api.v2.client.Response
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import io.kotest.matchers.should

fun <T : Any> isSuccess() = Matcher<Response<T>> {
    MatcherResult(
        it is Response.Success<*>,
        { "Indicates a response failure. problem=${(it as Response.Error).errors}\n${it.errors.joinToString { "type=${it.type}, title=${it.title}, detail=${it.detail}" }}" },
        { "Indicates a response failure." },
    )
}

fun <T : Any> Response<T>.shouldSuccess() {
    this should isSuccess()
}
