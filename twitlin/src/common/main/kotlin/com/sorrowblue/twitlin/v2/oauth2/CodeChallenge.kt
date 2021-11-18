package com.sorrowblue.twitlin.v2.oauth2

import com.sorrowblue.twitlin.utils.sha256
import io.ktor.util.InternalAPI
import io.ktor.util.encodeBase64
import kotlin.random.Random

/**
 * Code challenge
 *
 * @property codeChallengeString
 * @property method
 * @constructor Create empty Code challenge
 */
public sealed class CodeChallenge(public val codeChallengeString: String, public val method: String) {

    /**
     * S h a256
     *
     * @constructor
     *
     * @param codeVerifier
     */
    public class SHA256(public val codeVerifier: String = generateCodeVerifier()) :
        CodeChallenge(sha256(codeVerifier.encodeToByteArray()).encodeBase64URL(), "s256")

    /**
     * Plain
     *
     * @constructor
     *
     * @param plainCodeVerifier
     */
    public class Plain(public val plainCodeVerifier: String = generateCodeVerifier()) : CodeChallenge(plainCodeVerifier, "plain")

    public companion object {
        public fun generateCodeVerifier(length: Int = 128): String {
            val possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-._~"
            return (1..length).joinToString("") { possible[Random.nextInt(possible.length)].toString() }
        }
    }
}

@OptIn(InternalAPI::class)
internal fun ByteArray.encodeBase64URL(): String =
    encodeBase64()
        .trimEnd('=')
        .replace('+', '-')
        .replace('/', '_')
