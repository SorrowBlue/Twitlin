package com.sorrowblue.twitlin.core

import kotlin.test.Test
import kotlin.test.assertEquals

class AuthorizingRequestKtTest {

    private val collectedParameters = collectingParameters(
        CONSUMER_KEY,
        NONCE,
        SIGNATURE,
        TIMESTAMP,
        OAUTH_TOKEN,
        PARAMETERS
    )

    @Test
    fun testCollectingParameters() {
        val list = listOf(
            "oauth_consumer_key" to CONSUMER_KEY,
            "oauth_nonce" to NONCE,
            "oauth_signature" to SIGNATURE,
            "oauth_signature_method" to "HMAC-SHA1",
            "oauth_timestamp" to TIMESTAMP,
            "oauth_token" to OAUTH_TOKEN,
            "oauth_version" to "1.0"
        )
        assertEquals(list, collectedParameters)
    }

    @Test
    fun testBuildHeaderString() {
        val headerString = buildHeaderString(collectedParameters)
        assertEquals(
            "OAuth oauth_consumer_key=\"xvz1evFS4wEEPTGEFPHBog\", oauth_nonce=\"kYjzVBB8Y0ZFabxSWbWovY3uYSQ2pTgmZeNu2VS4cg\", oauth_signature=\"tnnArxj06cWHq44gCs1OSKk%2FjLY%3D\", oauth_signature_method=\"HMAC-SHA1\", oauth_timestamp=\"1318622958\", oauth_token=\"370773112-GmHxMAgYyLbNEtIKZeRNFsMKPR9EyMZeS9weJAEb\", oauth_version=\"1.0\"",
            headerString
        )
    }
}
