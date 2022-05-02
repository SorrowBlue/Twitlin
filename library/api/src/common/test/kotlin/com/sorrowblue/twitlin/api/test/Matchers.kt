package com.sorrowblue.twitlin.api.test

import com.sorrowblue.twitlin.api.client.Response
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import io.kotest.matchers.should

fun <T : Any> isSuccess() = Matcher<Response<T>> {
    MatcherResult(
        it is Response.Success<*>,
        { "Indicates a response failure. problem=${(it as Response.Error).errors}" },
        { "Indicates a response failure." },
    )
}

fun <T : Any> Response<T>.shouldSuccess() {
    this should isSuccess()
}
