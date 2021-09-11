package com.sorrowblue.twitlin.v2.oauth2

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
public data class OAuth2Token(
    @SerialName("token_type") val tokenType: String,
    @SerialName("expires_in") val expiresIn: Int,
    @SerialName("access_token") val accessToken: String,
    @Serializable(with = OAuth2ScopeListSerializer::class) val scope: List<OAuth2Scope>,
    @SerialName("refresh_token") val refreshToken: String
)

private object OAuth2ScopeListSerializer : KSerializer<List<OAuth2Scope>> {

    override fun deserialize(decoder: Decoder): List<OAuth2Scope> =
        decoder.decodeString().split(" ").mapNotNull { s ->
            OAuth2Scope.values().find { s == it.value }
        }

    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("OAuth2ScopeList", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: List<OAuth2Scope>) {
        encoder.encodeString(value.joinToString(" "))
    }
}
