package com.sorrowblue.twitlin.api.tweets

import com.sorrowblue.twitlin.api.client.Response
import com.sorrowblue.twitlin.api.tweets.statuses.StreamingMessage
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.types.shouldBeInstanceOf
import kotlinx.serialization.json.Json

class StreamingMessageTest : FunSpec({

   val json = Json {
       isLenient = true
       ignoreUnknownKeys = true
   }

    test("Blank lines (empty)") {
        val jsonBody = """
            {
            }
        """.trimIndent()
        json.decodeFromString(Response.serializer(StreamingMessage.Empty.serializer()), jsonBody).dataOrNull()
            .shouldBeInstanceOf<StreamingMessage.Empty>()
    }

    test("Limit notices (limit)") {
        val jsonBody = """
            {
              "limit":{
                "track":1234,
                "timestamp_ms": "1649652068731"
              }
            }
        """.trimIndent()
        json.decodeFromString(Response.serializer(StreamingMessage.serializer()), jsonBody).dataOrNull()
            .shouldBeInstanceOf<StreamingMessage.Limit>()
    }


    test("Disconnect messages (disconnect)") {
        val jsonBody = """
            {
            "disconnect":{
            "code": 4,
            "stream_name":"",
            "reason":""
            }
            }
        """.trimIndent()
        json.decodeFromString(Response.serializer(StreamingMessage.serializer()), jsonBody).dataOrNull()
            .shouldBeInstanceOf<StreamingMessage.Disconnect>()
    }


    test("Stall warnings (warning)") {
        val jsonBody = """
            {
            "warning":{
            "code":"FALLING_BEHIND",
            "message":"Your connection is falling behind and messages are being queued for delivery to you. Your queue is now over 60% full. You will be disconnected when the queue is full.",
            "percent_full": 60
            }
            }
        """.trimIndent()
        json.decodeFromString(Response.serializer(StreamingMessage.serializer()), jsonBody).dataOrNull()
            .shouldBeInstanceOf<StreamingMessage.Warning>()
    }


    test("Status deletion notices (delete)") {
        val jsonBody = """
            {
            "delete":{
            "status":{
            "id":1234,
            "id_str":"1234",
            "user_id":3,
            "user_id_str":"3"
            }
            }
            }
        """.trimIndent()
        json.decodeFromString(Response.serializer(StreamingMessage.serializer()), jsonBody).dataOrNull()
            .shouldBeInstanceOf<StreamingMessage.Delete>()
    }


    test("Location deletion notices (scrub_geo)") {
        val jsonBody = """
            {
            "scrub_geo":{
            "user_id":14090452,
            "user_id_str":"14090452",
            "up_to_status_id":23260136625,
            "up_to_status_id_str":"23260136625"
            }
            }
        """.trimIndent()
        json.decodeFromString(Response.serializer(StreamingMessage.serializer()), jsonBody).dataOrNull()
            .shouldBeInstanceOf<StreamingMessage.ScrubGeo>()
    }


    test("LWithheld content notices (status_withheld)") {
        val jsonBody = """
            {
            "status_withheld":{
            "id":1234567890,
            "user_id":123456,
            "withheld_in_countries":["DE", "AR"]
            }
            }
        """.trimIndent()
        json.decodeFromString(Response.serializer(StreamingMessage.serializer()), jsonBody).dataOrNull()
            .shouldBeInstanceOf<StreamingMessage.StatusWithheld>()
    }


    test("Withheld content notices (user_withheld)") {
        val jsonBody = """
            {
            "user_withheld":{
            "id":123456,
            "withheld_in_countries":["DE","AR"]
            }
            }
        """.trimIndent()
        json.decodeFromString(Response.serializer(StreamingMessage.serializer()), jsonBody).dataOrNull()
            .shouldBeInstanceOf<StreamingMessage.UserWithheld>()
    }
})
