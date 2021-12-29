package com.sorrowblue.twitlin.v2.client

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@AndroidParcelize
@Serializable
public data class Error(
    val detail: String? = null,
    val title: String? = null,
    val details: List<String>? = null,
    val type: Type? = null,
    val id: String? = null,
    @SerialName("resource_type")
    val resourceType: String? = null,
    val field: String? = null,
    val parameter: String? = null,
    val value: String? = null,
    val section: String? = null,
    val parameters: Map<String, List<String>>? = null,
    val message: String? = null
) : AndroidParcelable, JvmSerializable {

    @Serializable(with = ErrorTypeSerializer::class)
    public enum class Type(public val value: String) {

        /**
         * about:blank
         * A generic problem with no additional information beyond that provided by the HTTP status code.
         */
        @SerialName("about:blank")
        GENERIC_PROBLEM("about:blank"),

        /**
         * https://api.twitter.com/2/problems/invalid-request
         * A problem that indicates this request is invalid.
         */
        @SerialName("https://api.twitter.com/2/problems/invalid-request")
        INVALID_REQUEST_PROBLEM("https://api.twitter.com/2/problems/invalid-request"),

        /**
         * https://api.twitter.com/2/problems/resource-not-found
         * A problem that indicates that a given Tweet, User, etc. does not exist.
         */
        @SerialName("https://api.twitter.com/2/problems/resource-not-found")
        RESOURCE_NOT_FOUND("https://api.twitter.com/2/problems/resource-not-found"),

        /**
         * https://api.twitter.com/2/problems/not-authorized-for-resource
         * A problem that indicates you are not allowed to see a particular Tweet, User, etc.
         */
        @SerialName("https://api.twitter.com/2/problems/not-authorized-for-resource")
        RESOURCE_UNAUTHORIZED_PROBLEM("https://api.twitter.com/2/problems/not-authorized-for-resource"),

        /**
         * https://api.twitter.com/2/problems/not-authorized-for-field
         * A problem that indicates you are not allowed to see a particular Tweet, User, etc.
         */
        @SerialName("https://api.twitter.com/2/problems/not-authorized-for-field")
        FIELD_UNAUTHORIZED_PROBLEM("https://api.twitter.com/2/problems/not-authorized-for-field"),

        /**
         * https://api.twitter.com/2/problems/client-forbidden
         * A problem that indicates your client is forbidden from making this request.
         */
        @SerialName("https://api.twitter.com/2/problems/client-forbidden")
        CLIENT_FORBIDDEN_PROBLEM("https://api.twitter.com/2/problems/client-forbidden"),

        /**
         * https://api.twitter.com/2/problems/disallowed-resource
         * A problem that indicates that the resource requested violates the precepts of this API.
         */
        @SerialName("https://api.twitter.com/2/problems/disallowed-resource")
        DISALLOWED_RESOURCE("https://api.twitter.com/2/problems/disallowed-resource"),

        /**
         * https://api.twitter.com/2/problems/unsupported-authentication
         * A problem that indicates that the authentication used is not supported.
         */
        @SerialName("https://api.twitter.com/2/problems/unsupported-authentication")
        UNSUPPORTED_AUTHENTICATION_PROBLEM("https://api.twitter.com/2/problems/unsupported-authentication"),

        /**
         * https://api.twitter.com/2/problems/usage-capped
         * A problem that indicates that a usage cap has been exceeded.
         */
        @SerialName("https://api.twitter.com/2/problems/usage-capped")
        USAGE_CAPPED_PROBLEM("https://api.twitter.com/2/problems/usage-capped"),

        /**
         * https://api.twitter.com/2/problems/streaming-connection
         * A problem that indicates something is wrong with the connection.
         */
        @SerialName("https://api.twitter.com/2/problems/streaming-connection")
        CONNECTION_EXCEPTION_PROBLEM("https://api.twitter.com/2/problems/streaming-connection"),

        /**
         * https://api.twitter.com/2/problems/client-disconnected
         * Your client has gone away.
         */
        @SerialName("https://api.twitter.com/2/problems/client-disconnected")
        CLIENT_DISCONNECTED_PROBLEM("https://api.twitter.com/2/problems/client-disconnected"),

        /**
         * https://api.twitter.com/2/problems/operational-disconnect
         * You have been disconnected for operational reasons.
         */
        @SerialName("https://api.twitter.com/2/problems/operational-disconnect")
        OPERATIONAL_DISCONNECT_PROBLEM("https://api.twitter.com/2/problems/operational-disconnect"),

        /**
         * https://api.twitter.com/2/problems/rule-cap
         * You have exceeded the maximum number of rules.
         */
        @SerialName("https://api.twitter.com/2/problems/rule-cap")
        RILE_CAP_PROBLEM("https://api.twitter.com/2/problems/rule-cap"),

        /**
         * https://api.twitter.com/2/problems/invalid-rules
         * The rule you have submitted is invalid.
         */
        @SerialName("https://api.twitter.com/2/problems/invalid-rules")
        INVALID_RULES_PROBLEM("https://api.twitter.com/2/problems/invalid-rules"),

        /**
         * https://api.twitter.com/2/problems/duplicate-rules
         * The rule you have submitted is a duplicate.
         */
        @SerialName("https://api.twitter.com/2/problems/duplicate-rules")
        DUPLICATE_RULES_PROBLEM("https://api.twitter.com/2/problems/duplicate-rules"),

        /**
         * unknown
         */
        UNDEFINED("undefined"),
    }
}

@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = Error.Type::class)
internal object ErrorTypeSerializer : KSerializer<Error.Type> {

    override fun serialize(encoder: Encoder, value: Error.Type) = encoder.encodeString(value.value)

    override fun deserialize(decoder: Decoder) =
        kotlin.runCatching { Error.Type.valueOf(decoder.decodeString()) }.getOrElse { Error.Type.UNDEFINED }
}
