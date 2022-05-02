package com.sorrowblue.twitlin.api.v2.oauth2.impl

import com.sorrowblue.twitlin.api.v2.oauth2.OAuth2Scopes
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

internal object OAuthScopesListSerializer : KSerializer<List<OAuth2Scopes>> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("OAuthScopesList", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): List<OAuth2Scopes> {
        return decoder.decodeString().split(' ').mapNotNull { raw -> OAuth2Scopes.values().find { it.value == raw } }
    }

    override fun serialize(encoder: Encoder, value: List<OAuth2Scopes>) {
        encoder.encodeString(value.joinToString(" ") { it.value })
    }
}
