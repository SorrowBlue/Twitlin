package com.sorrowblue.twitlin.v2.oauth2

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.annotation.KotlinIgnoredOnParcel
import com.sorrowblue.twitlin.client.RateLimitInfo
import com.sorrowblue.twitlin.client.TwitterResponse
import com.sorrowblue.twitlin.v2.oauth2.impl.OAuthScopesListSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject

/**
 * O auth2response
 *
 * @constructor Create empty O auth2response
 */
@Serializable(with = OAuth2ResponseSerializer::class)
public sealed class OAuth2Response : TwitterResponse

internal object OAuth2ResponseSerializer : JsonContentPolymorphicSerializer<OAuth2Response>(OAuth2Response::class) {
    override fun selectDeserializer(element: JsonElement) = when {
        "error" in element.jsonObject -> OAuth2Error.serializer()
        else -> OAuth2Token.serializer()
    }
}

/**
 * O auth2error
 *
 * @property error
 * @property error_description
 * @constructor Create empty O auth2error
 */
@Serializable
public class OAuth2Error(public val error: String, public val error_description: String) : OAuth2Response() {
    @Transient
    override lateinit var rateLimitInfo: RateLimitInfo
}

/**
 * O auth2token
 *
 * @property tokenType
 * @property expiresIn
 * @property accessToken
 * @property scope
 * @property refreshToken
 * @constructor Create empty O auth2token
 */
@AndroidParcelize
@Serializable
public data class OAuth2Token(
    @SerialName("token_type")
    val tokenType: String,
    @SerialName("expires_in")
    val expiresIn: Int,
    @SerialName("access_token")
    val accessToken: String,
    @Serializable(OAuthScopesListSerializer::class)
    val scope: List<OAuth2Scopes>,
    @SerialName("refresh_token")
    val refreshToken: String,
) : OAuth2Response(), AndroidParcelable, JvmSerializable {
    @Transient
    @KotlinIgnoredOnParcel
    override lateinit var rateLimitInfo: RateLimitInfo
}
