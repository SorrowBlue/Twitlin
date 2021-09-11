/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.client

import com.sorrowblue.twitlin.BuildKonfig
import com.sorrowblue.twitlin.authentication.AccessToken
import com.sorrowblue.twitlin.v2.getApi
import com.sorrowblue.twitlin.v2.oauth2.CodeChallenge
import com.sorrowblue.twitlin.v2.oauth2.OAuth2Api
import com.sorrowblue.twitlin.v2.oauth2.OAuth2Scope
import com.sorrowblue.twitlin.v2.testResult
import com.sorrowblue.twitlin.v2.users.UsersApi
import io.ktor.util.InternalAPI
import io.ktor.util.encodeBase64
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonEncoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonObject
import test.AbstractTest
import test.logger

class ResponseTest : AbstractTest {

    @Test
    fun testResponseSuccess() {
        val json = Json
        val a: TestResponse<Int> =
            json.decodeFromString(TestResponseSerializer(Int.serializer()), "{\"data\":0}")
        assertEquals(TestResponse(0), a)
    }

    @OptIn(InternalAPI::class)
    @Test
    fun authorizeTest() = runBlocking {
        val state = Random.nextBytes(32).encodeBase64()
        OAuth2Api(OAuth2Client("MkFMT1pMT0tDYll5OXpQQ25xamI6MTpjaQ")).authorize(
            "https://maitter.sorrowblue.com",
            OAuth2Scope.all(),
            state,
            CodeChallenge.Plain("test")
        ).also {
            println("state = $state")
            println(it)
        }
    }

    @OptIn(InternalAPI::class)
    @Test
    fun usersTest() = runBlocking {
        OAuth2Api(OAuth2Client("MkFMT1pMT0tDYll5OXpQQ25xamI6MTpjaQ")).token(
            "N2JkQUZOS21QajJCVlFhbFVlX1dRNFhsWFBfeHYtbHQxdE5CWi0yMV9aSTFsOjE2MzEzNTgyODc2MDM6MToxOmFjOjE",
            "https://maitter.sorrowblue.com",
            "test"
        )
    }

    // access_token: djlIa2duOUJabFF6MGppTm96Y3VmTkU4RmJtbXdHTXNqOVM1bXNBMmpEVmRmOjE2MzEzNDgxOTEzOTU6MToxOmF0OjE
    // refresh_token: WXJ6aVQwZS01RFdfMnk5bl9LeHJwNW9DbFBseENURlFZeFA0em9ZV3M0RWtoOjE2MzEzNDgxOTEzOTU6MToxOnJ0OjE
    @Test
    fun tweetTest() = runBlocking {
        getApi(OAuth1aClient(
//            "MkFMT1pMT0tDYll5OXpQQ25xamI6MTpjaQ", "V0Roc0ZrNzNiWnpQeGhZSUt6bDh2NVRIZUZUMU1qOWdoZmo3N0pOTy1BQzZ5OjE2MzEzNTgzMDEyMzA6MToxOmF0OjE"
            ConsumerKeys(BuildKonfig.TWITTER_API_KEY, BuildKonfig.TWITTER_API_SECRET),
            AccessToken(BuildKonfig.TWITTER_API_ACCESS_TOKEN, BuildKonfig.TWITTER_API_ACCESS_TOKEN_SECRET, "", "")
        ), UsersApi::class).byUsername(listOf("Ao_Sankaku")).testResult()
    }
}

@Serializable(TestResponseSerializer::class)
data class TestResponse<T : Any>(val data: T)

@OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
@Serializer(forClass = TestResponse::class)
internal class TestResponseSerializer<T : Any>(private val dataSerializer: KSerializer<T>) :
    KSerializer<TestResponse<T>> {

    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor("TestResponse") {
            element<Int>("data")
        }

    override fun deserialize(decoder: Decoder): TestResponse<T> {
//         Decoder -> JsonDecoder
        require(decoder is JsonDecoder) // this class can be decoded only by Json
//         JsonDecoder -> JsonElement
        val element = kotlin.runCatching {
            decoder.decodeJsonElement()
        }.getOrElse { JsonObject(emptyMap()) }
//         JsonElement -> value
        logger.debug { "JSON: $element" }
        return TestResponse(
            decoder.json.decodeFromJsonElement(
                dataSerializer,
                element.jsonObject["data"]!!
            )
        )
    }

    override fun serialize(encoder: Encoder, value: TestResponse<T>) {
//        Encoder -> JsonEncoder
        require(encoder is JsonEncoder) // This class can be encoded only by Json
//        value -> JsonElement
        val element = encoder.json.encodeToJsonElement(value)
//        JsonElement -> JsonEncoder
        encoder.encodeJsonElement(element)
    }
}
