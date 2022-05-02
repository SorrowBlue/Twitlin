package com.sorrowblue.twitlin.api.v2.client

import com.sorrowblue.twitlin.api.v2.objects.OptionalData
import com.sorrowblue.twitlin.api.v2.objects.Tweet
import com.sorrowblue.twitlin.api.v2.test.NonApiTest
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.types.shouldBeInstanceOf
import kotlinx.serialization.json.Json

class ResponseTest : FunSpec({

    tags(NonApiTest)

    test("Response.Success") {
        val json = """
            {
                "data": {
                    "id": "126855",
                    "text": "aaaaaaaaaaaaaaaa"
                }
            }
        """.trimIndent()
        Json.decodeFromString(Response.serializer(OptionalData.serializer(Tweet.serializer())), json)
            .shouldBeInstanceOf<Response.Success<OptionalData<Tweet>>>()
    }

    test("Response.Error") {
        val json = """
            {"title":"Forbidden","type":"about:blank","detail":"Forbidden","status": "400"}
        """.trimIndent()
        Json.decodeFromString(Response.serializer(OptionalData.serializer(Tweet.serializer())), json).let {
            it.shouldBeInstanceOf<Response.Error<OptionalData<Tweet>>>()
            (it as Response.Error<*>).errors.first().shouldBeInstanceOf<GenericProblem>()
        }
    }
})
