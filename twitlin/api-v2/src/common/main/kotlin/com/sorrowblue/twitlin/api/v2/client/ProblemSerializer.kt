package com.sorrowblue.twitlin.api.v2.client

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.descriptors.PolymorphicKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildSerialDescriptor
import kotlinx.serialization.encodeToString
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonPrimitive

@OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
internal object ProblemSerializer : KSerializer<Problem> {

    override val descriptor: SerialDescriptor = buildSerialDescriptor("Problem", PolymorphicKind.SEALED)

    override fun serialize(encoder: Encoder, value: Problem) = encoder.encodeString(Json.encodeToString(value))

    override fun deserialize(decoder: Decoder): Problem {
        require(decoder is JsonDecoder)
        val element = runCatching(decoder::decodeJsonElement).getOrElse { JsonObject(emptyMap()) }
        if (element !is JsonObject) {
            return UndefinedProblem()
        }
        return if ("type" in element) {
            when (Json.decodeFromString<ProblemType>(element.getValue("type").jsonPrimitive.toString())) {
                ProblemType.GENERIC -> Json.decodeFromJsonElement<GenericProblem>(element)
                ProblemType.INVALID_REQUEST -> Json.decodeFromJsonElement<InvalidRequestProblem>(element)
                ProblemType.RESOURCE_NOT_FOUND -> Json.decodeFromJsonElement<ResourceNotFoundProblem>(element)
                ProblemType.NOT_AUTHORIZED_FOR_RESOURCE ->
                    Json.decodeFromJsonElement<ResourceUnauthorizedProblem>(element)
                ProblemType.RESOURCE_UNAVAILABLE -> Json.decodeFromJsonElement<ResourceUnavailableProblem>(element)
                ProblemType.FIELD_UNAUTHORIZED -> Json.decodeFromJsonElement<FieldUnauthorizedProblem>(element)
                ProblemType.CLIENT_FORBIDDEN -> Json.decodeFromJsonElement<ClientForbiddenProblem>(element)
                ProblemType.DISALLOWED_RESOURCE -> Json.decodeFromJsonElement<DisallowedResourceProblem>(element)
                ProblemType.UNSUPPORTED_AUTHENTICATION ->
                    Json.decodeFromJsonElement<UnsupportedAuthenticationProblem>(element)
                ProblemType.USAGE_CAPPED -> Json.decodeFromJsonElement<UsageCapExceededProblem>(element)
                ProblemType.STREAMING_CONNECTION -> Json.decodeFromJsonElement<ConnectionExceptionProblem>(element)
                ProblemType.CLIENT_DISCONNECTED -> Json.decodeFromJsonElement<ClientDisconnectedProblem>(element)
                ProblemType.OPERATIONAL_DISCONNECT -> Json.decodeFromJsonElement<OperationalDisconnectProblem>(element)
                ProblemType.RULE_CAP -> Json.decodeFromJsonElement<RulesCapProblem>(element)
                ProblemType.INVALID_RULES -> Json.decodeFromJsonElement<InvalidRuleProblem>(element)
                ProblemType.DUPLICATE_RULES -> Json.decodeFromJsonElement<DuplicateRuleProblem>(element)
                ProblemType.CONFLICT -> Json.decodeFromJsonElement<ConflictProblem>(element)
                ProblemType.NONCOMPLIANT_RULES -> Json.decodeFromJsonElement<NonCompliantRulesProblem>(element)
                ProblemType.UNDEFINED -> UndefinedProblem()
            }
        } else {
            UndefinedProblem()
        }
    }
}
