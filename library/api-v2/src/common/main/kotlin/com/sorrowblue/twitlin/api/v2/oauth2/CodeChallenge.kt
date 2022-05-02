package com.sorrowblue.twitlin.api.v2.oauth2

import io.ktor.util.Digest
import io.ktor.util.encodeBase64
import kotlin.random.Random

/**
 * Code challenge
 *
 * @property method
 */
public sealed class CodeChallenge(public val method: String) {

    /**
     * Code challenge string
     *
     * @return
     */
    public abstract suspend fun codeChallengeString(): String

    /**
     * S h a256
     *
     * @constructor
     *
     * @param codeVerifier
     */
    public class SHA256(public val codeVerifier: String = generateCodeVerifier()) : CodeChallenge("s256") {

        /**
         * Code challenge string
         *
         * @return
         */
        override suspend fun codeChallengeString(): String {
            return sha256(codeVerifier.encodeToByteArray()).encodeBase64URL()
        }
    }

    /**
     * Plain
     *
     * @constructor
     *
     * @param plainCodeVerifier
     */
    public class Plain(private val plainCodeVerifier: String = generateCodeVerifier()) : CodeChallenge("plain") {

        /**
         * Code challenge string
         *
         * @return
         */
        override suspend fun codeChallengeString(): String = plainCodeVerifier
    }

    public companion object {
        public fun generateCodeVerifier(length: Int = 128): String {
            val possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-._~"
            return (1..length).joinToString("") { possible[Random.nextInt(possible.length)].toString() }
        }
    }
}

internal suspend fun sha256(value: ByteArray): ByteArray {
    return Digest("SHA-256").apply {
        plusAssign(value)
    }.build()
}

internal fun ByteArray.encodeBase64URL(): String =
    encodeBase64()
        .trimEnd('=')
        .replace('+', '-')
        .replace('/', '_')
