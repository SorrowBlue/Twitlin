package com.sorrowblue.twitlin.api.client

import com.sorrowblue.twitlin.api.ktx.CONSUMER_KEY
import com.sorrowblue.twitlin.api.ktx.CONSUMER_SECRET
import com.sorrowblue.twitlin.api.ktx.METHOD
import com.sorrowblue.twitlin.api.ktx.NONCE
import com.sorrowblue.twitlin.api.ktx.OAUTH_TOKEN
import com.sorrowblue.twitlin.api.ktx.OAUTH_TOKEN_SECRET
import com.sorrowblue.twitlin.api.ktx.PARAMETERS
import com.sorrowblue.twitlin.api.ktx.TIMESTAMP
import com.sorrowblue.twitlin.api.ktx.URL
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

/**
 * See [Authentication](https://developer.twitter.com/en/docs/authentication/oauth-1-0a/creating-a-signature).
 */
class CreatingSignatureKtTest : FunSpec({

    /**
     * Collecting parameters
     *
     * Parameter string: `include_entities=true&oauth_consumer_key=xvz1evFS4wEEPTGEFPHBog&oauth_nonce=kYjzVBB8Y0ZFabxSWbWovY3uYSQ2pTgmZeNu2VS4cg&oauth_signature_method=HMAC-SHA1&oauth_timestamp=1318622958&oauth_token=370773112-GmHxMAgYyLbNEtIKZeRNFsMKPR9EyMZeS9weJAEb&oauth_version=1.0&status=Hello%20Ladies%20%2B%20Gentlemen%2C%20a%20signed%20OAuth%20request%21`
     */
    test("collectingParameters") {
        val parameters = collectingParameters(CONSUMER_KEY, NONCE, TIMESTAMP, OAUTH_TOKEN, PARAMETERS)
        parameters shouldBe "include_entities=true&oauth_consumer_key=xvz1evFS4wEEPTGEFPHBog&oauth_nonce=kYjzVBB8Y0ZFabxSWbWovY3uYSQ2pTgmZeNu2VS4cg&oauth_signature_method=HMAC-SHA1&oauth_timestamp=1318622958&oauth_token=370773112-GmHxMAgYyLbNEtIKZeRNFsMKPR9EyMZeS9weJAEb&oauth_version=1.0&status=Hello%20Ladies%20%2B%20Gentlemen%2C%20a%20signed%20OAuth%20request%21"
    }

    /**
     * Creating the signature base string
     *
     * Base string: `POST&https%3A%2F%2Fapi.twitter.com%2F1.1%2Fstatuses%2Fupdate.json&include_entities%3Dtrue%26oauth_consumer_key%3Dxvz1evFS4wEEPTGEFPHBog%26oauth_nonce%3DkYjzVBB8Y0ZFabxSWbWovY3uYSQ2pTgmZeNu2VS4cg%26oauth_signature_method%3DHMAC-SHA1%26oauth_timestamp%3D1318622958%26oauth_token%3D370773112-GmHxMAgYyLbNEtIKZeRNFsMKPR9EyMZeS9weJAEb%26oauth_version%3D1.0%26status%3DHello%2520Ladies%2520%252B%2520Gentlemen%252C%2520a%2520signed%2520OAuth%2520request%2521`
     */
    test("creatingSignatureBaseString") {
        val parameters = collectingParameters(CONSUMER_KEY, NONCE, TIMESTAMP, OAUTH_TOKEN, PARAMETERS)
        val baseString = creatingSignatureBaseString(METHOD, URL, parameters)
        baseString shouldBe "POST&https%3A%2F%2Fapi.twitter.com%2F1.1%2Fstatuses%2Fupdate.json&include_entities%3Dtrue%26oauth_consumer_key%3Dxvz1evFS4wEEPTGEFPHBog%26oauth_nonce%3DkYjzVBB8Y0ZFabxSWbWovY3uYSQ2pTgmZeNu2VS4cg%26oauth_signature_method%3DHMAC-SHA1%26oauth_timestamp%3D1318622958%26oauth_token%3D370773112-GmHxMAgYyLbNEtIKZeRNFsMKPR9EyMZeS9weJAEb%26oauth_version%3D1.0%26status%3DHello%2520Ladies%2520%252B%2520Gentlemen%252C%2520a%2520signed%2520OAuth%2520request%2521"
    }

    /**
     * Getting a signing key
     *
     * Signing key: `kAcSOqF21Fu85e7zjz7ZN2U4ZRhfV3WpwPAoE3Z7kBw&LswwdoUaIvS8ltyTt5jkRh4J50vUPVVHtR2YPi5kE`
     */
    test("gettingSigningKey") {
        val signingKey = gettingSigningKey(CONSUMER_SECRET, OAUTH_TOKEN_SECRET)
        signingKey shouldBe "kAcSOqF21Fu85e7zjz7ZN2U4ZRhfV3WpwPAoE3Z7kBw&LswwdoUaIvS8ltyTt5jkRh4J50vUPVVHtR2YPi5kE"
    }

    /**
     * Calculating the signature
     *
     * Binary string: `0x842B5299887E88760212A056AC4EC2EE1626B549`
     */
    test("calculateSignature") {
        val signingKey = gettingSigningKey(CONSUMER_SECRET, OAUTH_TOKEN_SECRET)
        val parameters = collectingParameters(CONSUMER_KEY, NONCE, TIMESTAMP, OAUTH_TOKEN, PARAMETERS)
        val baseString = creatingSignatureBaseString(METHOD, URL, parameters)
        calculateSignature(baseString, signingKey) shouldBe "842B5299887E88760212A056AC4EC2EE1626B549".hexToByteArray()
    }

    /**
     * Calculating the signature
     *
     * Signature string: `hCtSmYh+iHYCEqBWrE7C7hYmtUk=`
     */
    test("CalculateSignatureBase64") {
        val signingKey = gettingSigningKey(CONSUMER_SECRET, OAUTH_TOKEN_SECRET)
        val parameters = collectingParameters(CONSUMER_KEY, NONCE, TIMESTAMP, OAUTH_TOKEN, PARAMETERS)
        val baseString = creatingSignatureBaseString(METHOD, URL, parameters)
        calculateSignatureBase64(baseString, signingKey) shouldBe "hCtSmYh+iHYCEqBWrE7C7hYmtUk="
    }
})

private fun String.hexToByteArray(): ByteArray {
    fun hexStringToByte(hexString: String) = hexString.toInt(16).toByte()
    return ByteArray(length / 2) {
        val pointer = it * 2
        hexStringToByte(substring(pointer, pointer + 2))
    }
}
