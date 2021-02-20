/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.core

import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * See [Authentication](https://developer.twitter.com/en/docs/authentication/oauth-1-0a/creating-a-signature).
 */
class CreatingSignatureKtTest {

    private val parametersString =
        collectingParameters(CONSUMER_KEY, NONCE, TIMESTAMP, OAUTH_TOKEN, PARAMETERS)

    private val baseString = creatingSignatureBaseString(METHOD, URL, parametersString)

    private val signingKey = gettingSigningKey(CONSUMER_SECRET, OAUTH_TOKEN_SECRET)

    /**
     * Collecting parameters
     *
     * Parameter string: `include_entities=true&oauth_consumer_key=xvz1evFS4wEEPTGEFPHBog&oauth_nonce=kYjzVBB8Y0ZFabxSWbWovY3uYSQ2pTgmZeNu2VS4cg&oauth_signature_method=HMAC-SHA1&oauth_timestamp=1318622958&oauth_token=370773112-GmHxMAgYyLbNEtIKZeRNFsMKPR9EyMZeS9weJAEb&oauth_version=1.0&status=Hello%20Ladies%20%2B%20Gentlemen%2C%20a%20signed%20OAuth%20request%21`
     */
    @Test
    fun testCollectingParameters() {
        assertEquals(
            "include_entities=true&oauth_consumer_key=xvz1evFS4wEEPTGEFPHBog&oauth_nonce=kYjzVBB8Y0ZFabxSWbWovY3uYSQ2pTgmZeNu2VS4cg&oauth_signature_method=HMAC-SHA1&oauth_timestamp=1318622958&oauth_token=370773112-GmHxMAgYyLbNEtIKZeRNFsMKPR9EyMZeS9weJAEb&oauth_version=1.0&status=Hello%20Ladies%20%2B%20Gentlemen%2C%20a%20signed%20OAuth%20request%21",
            parametersString
        )
    }

    /**
     * Creating the signature base string
     *
     * Base string: `POST&https%3A%2F%2Fapi.twitter.com%2F1.1%2Fstatuses%2Fupdate.json&include_entities%3Dtrue%26oauth_consumer_key%3Dxvz1evFS4wEEPTGEFPHBog%26oauth_nonce%3DkYjzVBB8Y0ZFabxSWbWovY3uYSQ2pTgmZeNu2VS4cg%26oauth_signature_method%3DHMAC-SHA1%26oauth_timestamp%3D1318622958%26oauth_token%3D370773112-GmHxMAgYyLbNEtIKZeRNFsMKPR9EyMZeS9weJAEb%26oauth_version%3D1.0%26status%3DHello%2520Ladies%2520%252B%2520Gentlemen%252C%2520a%2520signed%2520OAuth%2520request%2521`
     */
    @Test
    fun testCreatingSignatureBaseString() {
        assertEquals(
            "POST&https%3A%2F%2Fapi.twitter.com%2F1.1%2Fstatuses%2Fupdate.json&include_entities%3Dtrue%26oauth_consumer_key%3Dxvz1evFS4wEEPTGEFPHBog%26oauth_nonce%3DkYjzVBB8Y0ZFabxSWbWovY3uYSQ2pTgmZeNu2VS4cg%26oauth_signature_method%3DHMAC-SHA1%26oauth_timestamp%3D1318622958%26oauth_token%3D370773112-GmHxMAgYyLbNEtIKZeRNFsMKPR9EyMZeS9weJAEb%26oauth_version%3D1.0%26status%3DHello%2520Ladies%2520%252B%2520Gentlemen%252C%2520a%2520signed%2520OAuth%2520request%2521",
            baseString
        )
    }

    /**
     * Getting a signing key
     *
     * Signing key: `kAcSOqF21Fu85e7zjz7ZN2U4ZRhfV3WpwPAoE3Z7kBw&LswwdoUaIvS8ltyTt5jkRh4J50vUPVVHtR2YPi5kE`
     */
    @Test
    fun testGettingSigningKey() {
        assertEquals(
            "kAcSOqF21Fu85e7zjz7ZN2U4ZRhfV3WpwPAoE3Z7kBw&LswwdoUaIvS8ltyTt5jkRh4J50vUPVVHtR2YPi5kE",
            signingKey
        )
    }

    /**
     * Calculating the signature
     *
     * Binary string: `0x842B5299887E88760212A056AC4EC2EE1626B549`
     */
    @Test
    fun testCalculateSignature() {
        val signature = calculateSignature(baseString, signingKey)
        assertEquals(
            "842B5299887E88760212A056AC4EC2EE1626B549".hexToByteArray().asList(),
            signature.asList()
        )
    }

    /**
     * Calculating the signature
     *
     * Signature string: `hCtSmYh+iHYCEqBWrE7C7hYmtUk=`
     */
    @Test
    fun testCalculateSignatureBase64() {
        val signatureBase64 = calculateSignatureBase64(baseString, signingKey)
        assertEquals("hCtSmYh+iHYCEqBWrE7C7hYmtUk=", signatureBase64)
    }

    private fun String.hexToByteArray(): ByteArray {
        fun hexStringToByte(hexString: String) = hexString.toInt(16).toByte()
        return ByteArray(length / 2) {
            val pointer = it * 2
            hexStringToByte(substring(pointer, pointer + 2))
        }
    }
}
