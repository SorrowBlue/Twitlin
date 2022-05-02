package com.sorrowblue.twitlin.api.ktx

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

internal const val CONSUMER_KEY = "xvz1evFS4wEEPTGEFPHBog"
internal const val NONCE = "kYjzVBB8Y0ZFabxSWbWovY3uYSQ2pTgmZeNu2VS4cg"
internal const val TIMESTAMP = "1318622958"
internal const val CONSUMER_SECRET = "kAcSOqF21Fu85e7zjz7ZN2U4ZRhfV3WpwPAoE3Z7kBw"
internal const val OAUTH_TOKEN = "370773112-GmHxMAgYyLbNEtIKZeRNFsMKPR9EyMZeS9weJAEb"
internal const val OAUTH_TOKEN_SECRET = "LswwdoUaIvS8ltyTt5jkRh4J50vUPVVHtR2YPi5kE"
internal const val URL = "https://api.twitter.com/1.1/statuses/update.json"
internal const val METHOD = "POST"
internal const val SIGNATURE = "tnnArxj06cWHq44gCs1OSKk/jLY="

internal val PARAMETERS = listOf(
    "status" to "Hello Ladies + Gentlemen, a signed OAuth request!",
    "include_entities" to "true"
)

class AuthorizingRequestKtTest : FunSpec({

    val collectedParameters = collectingParameters(CONSUMER_KEY, NONCE, SIGNATURE, TIMESTAMP, OAUTH_TOKEN, PARAMETERS)

    test("collectedParameters") {
        val list = listOf(
            "oauth_consumer_key" to CONSUMER_KEY,
            "oauth_nonce" to NONCE,
            "oauth_signature" to SIGNATURE,
            "oauth_signature_method" to "HMAC-SHA1",
            "oauth_timestamp" to TIMESTAMP,
            "oauth_token" to OAUTH_TOKEN,
            "oauth_version" to "1.0"
        )
        collectedParameters shouldBe list
    }

    test("buildHeaderString") {
        val headerString = buildHeaderString(collectedParameters)
        headerString shouldBe "OAuth oauth_consumer_key=\"xvz1evFS4wEEPTGEFPHBog\", oauth_nonce=\"kYjzVBB8Y0ZFabxSWbWovY3uYSQ2pTgmZeNu2VS4cg\", oauth_signature=\"tnnArxj06cWHq44gCs1OSKk%2FjLY%3D\", oauth_signature_method=\"HMAC-SHA1\", oauth_timestamp=\"1318622958\", oauth_token=\"370773112-GmHxMAgYyLbNEtIKZeRNFsMKPR9EyMZeS9weJAEb\", oauth_version=\"1.0\""
    }
})
