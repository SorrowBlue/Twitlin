package com.sorrowblue.twitlin.v2

import com.sorrowblue.twitlin.foundation.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlin.test.Test
import kotlin.test.assertEquals

private const val CONSUMER_KEY = "xvz1evFS4wEEPTGEFPHBog"
private const val CONSUMER_SECRET_KEY = "kAcSOqF21Fu85e7zjz7ZN2U4ZRhfV3WpwPAoE3Z7kBw"
private const val OAUTH_TOKEN = "370773112-GmHxMAgYyLbNEtIKZeRNFsMKPR9EyMZeS9weJAEb"
private const val OAUTH_TOKEN_SECRET = "LswwdoUaIvS8ltyTt5jkRh4J50vUPVVHtR2YPi5kE"

private const val NONCE = "kYjzVBB8Y0ZFabxSWbWovY3uYSQ2pTgmZeNu2VS4cg"
private const val TIMESTAMP = "1318622958"

class ClientTest {
	@Test
	fun collectParametersForSignatureTest() {
		val parameterString = collectParametersForSignature(
			CONSUMER_KEY, NONCE, TIMESTAMP, OAUTH_TOKEN,
			listOf(
				"status" to "Hello Ladies + Gentlemen, a signed OAuth request!",
				"include_entities" to true
			)
		)
		assertEquals(
			"include_entities=true&oauth_consumer_key=xvz1evFS4wEEPTGEFPHBog&oauth_nonce=kYjzVBB8Y0ZFabxSWbWovY3uYSQ2pTgmZeNu2VS4cg&oauth_signature_method=HMAC-SHA1&oauth_timestamp=1318622958&oauth_token=370773112-GmHxMAgYyLbNEtIKZeRNFsMKPR9EyMZeS9weJAEb&oauth_version=1.0&status=Hello%20Ladies%20%2B%20Gentlemen%2C%20a%20signed%20OAuth%20request%21",
			parameterString
		)
	}

	@Test
	fun creatingSignatureBaseStringTest() {
		val parameterString =
			"include_entities=true&oauth_consumer_key=xvz1evFS4wEEPTGEFPHBog&oauth_nonce=kYjzVBB8Y0ZFabxSWbWovY3uYSQ2pTgmZeNu2VS4cg&oauth_signature_method=HMAC-SHA1&oauth_timestamp=1318622958&oauth_token=370773112-GmHxMAgYyLbNEtIKZeRNFsMKPR9EyMZeS9weJAEb&oauth_version=1.0&status=Hello%20Ladies%20%2B%20Gentlemen%2C%20a%20signed%20OAuth%20request%21"
		val baseString = HttpRequestBuilder().apply {
			url("https://api.twitter.com/1.1/statuses/update.json?include_entities=true")
			method = HttpMethod.Post
		}.creatingSignatureBaseString(parameterString)
		assertEquals(
			"POST&https%3A%2F%2Fapi.twitter.com%2F1.1%2Fstatuses%2Fupdate.json&include_entities%3Dtrue%26oauth_consumer_key%3Dxvz1evFS4wEEPTGEFPHBog%26oauth_nonce%3DkYjzVBB8Y0ZFabxSWbWovY3uYSQ2pTgmZeNu2VS4cg%26oauth_signature_method%3DHMAC-SHA1%26oauth_timestamp%3D1318622958%26oauth_token%3D370773112-GmHxMAgYyLbNEtIKZeRNFsMKPR9EyMZeS9weJAEb%26oauth_version%3D1.0%26status%3DHello%2520Ladies%2520%252B%2520Gentlemen%252C%2520a%2520signed%2520OAuth%2520request%2521",
			baseString
		)
	}

	@Test
	fun getSigningKey() {
		val signingKey =
			com.sorrowblue.twitlin.foundation.getSigningKey(CONSUMER_SECRET_KEY, OAUTH_TOKEN_SECRET)
		assertEquals(
			"kAcSOqF21Fu85e7zjz7ZN2U4ZRhfV3WpwPAoE3Z7kBw&LswwdoUaIvS8ltyTt5jkRh4J50vUPVVHtR2YPi5kE",
			signingKey
		)
		val noTokenSigningKey =
			com.sorrowblue.twitlin.foundation.getSigningKey(CONSUMER_SECRET_KEY, null)
		assertEquals("kAcSOqF21Fu85e7zjz7ZN2U4ZRhfV3WpwPAoE3Z7kBw&", noTokenSigningKey)
	}

	@Test
	fun calculateSignatureTest() {
		val signature = calculateSignature(
			"POST&https%3A%2F%2Fapi.twitter.com%2F1.1%2Fstatuses%2Fupdate.json&include_entities%3Dtrue%26oauth_consumer_key%3Dxvz1evFS4wEEPTGEFPHBog%26oauth_nonce%3DkYjzVBB8Y0ZFabxSWbWovY3uYSQ2pTgmZeNu2VS4cg%26oauth_signature_method%3DHMAC-SHA1%26oauth_timestamp%3D1318622958%26oauth_token%3D370773112-GmHxMAgYyLbNEtIKZeRNFsMKPR9EyMZeS9weJAEb%26oauth_version%3D1.0%26status%3DHello%2520Ladies%2520%252B%2520Gentlemen%252C%2520a%2520signed%2520OAuth%2520request%2521",
			"kAcSOqF21Fu85e7zjz7ZN2U4ZRhfV3WpwPAoE3Z7kBw&LswwdoUaIvS8ltyTt5jkRh4J50vUPVVHtR2YPi5kE"
		)
		assertEquals("hCtSmYh+iHYCEqBWrE7C7hYmtUk=", signature)
	}

	@Test
	fun createSignatureTest() {
		val signature = HttpRequestBuilder().apply {
			url("https://api.twitter.com/1.1/statuses/update.json?include_entities=true")
			method = HttpMethod.Post
		}.createSignature(
			CONSUMER_KEY, CONSUMER_SECRET_KEY, NONCE, TIMESTAMP, OAUTH_TOKEN, OAUTH_TOKEN_SECRET,
			listOf(
				"status" to "Hello Ladies + Gentlemen, a signed OAuth request!",
				"include_entities" to true
			)
		)
		assertEquals("hCtSmYh+iHYCEqBWrE7C7hYmtUk=", signature)
	}

	@Test
	fun buildHeaderStringTest() {
		val headerString = buildHeaderString(
			CONSUMER_KEY, NONCE, "tnnArxj06cWHq44gCs1OSKk/jLY=", TIMESTAMP, OAUTH_TOKEN
		)
		assertEquals(
			"OAuth oauth_consumer_key=\"xvz1evFS4wEEPTGEFPHBog\", oauth_nonce=\"kYjzVBB8Y0ZFabxSWbWovY3uYSQ2pTgmZeNu2VS4cg\", oauth_signature=\"tnnArxj06cWHq44gCs1OSKk%2FjLY%3D\", oauth_signature_method=\"HMAC-SHA1\", oauth_timestamp=\"1318622958\", oauth_token=\"370773112-GmHxMAgYyLbNEtIKZeRNFsMKPR9EyMZeS9weJAEb\", oauth_version=\"1.0\"",
			headerString
		)
	}
}
