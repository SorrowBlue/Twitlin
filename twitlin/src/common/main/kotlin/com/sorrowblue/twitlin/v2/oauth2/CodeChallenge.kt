package com.sorrowblue.twitlin.v2.oauth2

import com.sorrowblue.twitlin.utils.Security
import io.ktor.util.InternalAPI
import io.ktor.util.encodeBase64

public sealed class CodeChallenge(public val codeChallenge: String, public val method: String) {

    @OptIn(InternalAPI::class)
    public class SHA256(codeVerifier: String) :
        CodeChallenge(Security.sha256(codeVerifier.encodeToByteArray()).encodeBase64(), "s256")

    public class Plain(plainCodeVerifier: String) : CodeChallenge(plainCodeVerifier, "plain")
}
