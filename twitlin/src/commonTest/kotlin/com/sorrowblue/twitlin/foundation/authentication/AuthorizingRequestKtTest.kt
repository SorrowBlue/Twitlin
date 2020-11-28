package com.sorrowblue.twitlin.foundation.authentication

import kotlin.test.Test
import kotlin.test.assertEquals


private const val CONSUMER_KEY = "xvz1evFS4wEEPTGEFPHBog"
private const val NONCE = "kYjzVBB8Y0ZFabxSWbWovY3uYSQ2pTgmZeNu2VS4cg"
private const val TIMESTAMP = "1318622958"
private const val OAUTH_TOKEN = "370773112-GmHxMAgYyLbNEtIKZeRNFsMKPR9EyMZeS9weJAEb"
private const val SIGNATURE = "tnnArxj06cWHq44gCs1OSKk/jLY="

class AuthorizingRequestKtTest {

    @Test
    fun buildHeaderStringTest() {
        val params = collectParameters(CONSUMER_KEY, NONCE, SIGNATURE, TIMESTAMP, OAUTH_TOKEN)
        val headerString = buildHeaderString(params)
        assertEquals(
            "OAuth oauth_consumer_key=\"xvz1evFS4wEEPTGEFPHBog\", oauth_nonce=\"kYjzVBB8Y0ZFabxSWbWovY3uYSQ2pTgmZeNu2VS4cg\", oauth_signature=\"tnnArxj06cWHq44gCs1OSKk%2FjLY%3D\", oauth_signature_method=\"HMAC-SHA1\", oauth_timestamp=\"1318622958\", oauth_token=\"370773112-GmHxMAgYyLbNEtIKZeRNFsMKPR9EyMZeS9weJAEb\", oauth_version=\"1.0\"",
            headerString
        )
    }
}
