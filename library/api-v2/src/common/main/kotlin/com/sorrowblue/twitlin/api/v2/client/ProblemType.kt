package com.sorrowblue.twitlin.api.v2.client

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class ProblemType {

    @SerialName("about:blank")
    GENERIC,

    @SerialName("https://api.twitter.com/2/problems/invalid-request")
    INVALID_REQUEST,

    @SerialName("https://api.twitter.com/2/problems/resource-not-found")
    RESOURCE_NOT_FOUND,

    @SerialName("https://api.twitter.com/2/problems/not-authorized-for-resource")
    NOT_AUTHORIZED_FOR_RESOURCE,

    @SerialName("https://api.twitter.com/2/problems/resource-unavailable")
    RESOURCE_UNAVAILABLE,

    @SerialName("https://api.twitter.com/2/problems/not-authorized-for-field")
    FIELD_UNAUTHORIZED,

    @SerialName("https://api.twitter.com/2/problems/client-forbidden")
    CLIENT_FORBIDDEN,

    @SerialName("https://api.twitter.com/2/problems/disallowed-resource")
    DISALLOWED_RESOURCE,

    @SerialName("https://api.twitter.com/2/problems/unsupported-authentication")
    UNSUPPORTED_AUTHENTICATION,

    @SerialName("https://api.twitter.com/2/problems/usage-capped")
    USAGE_CAPPED,

    @SerialName("https://api.twitter.com/2/problems/streaming-connection")
    STREAMING_CONNECTION,

    @SerialName("https://api.twitter.com/2/problems/client-disconnected")
    CLIENT_DISCONNECTED,

    @SerialName("https://api.twitter.com/2/problems/operational-disconnect")
    OPERATIONAL_DISCONNECT,

    @SerialName("https://api.twitter.com/2/problems/rule-cap")
    RULE_CAP,

    @SerialName("https://api.twitter.com/2/problems/invalid-rules")
    INVALID_RULES,

    @SerialName("https://api.twitter.com/2/problems/duplicate-rules")
    DUPLICATE_RULES,

    @SerialName("https://api.twitter.com/2/problems/conflict")
    CONFLICT,

    @SerialName("https://api.twitter.com/2/problems/noncompliant-rules")
    NONCOMPLIANT_RULES,

    UNDEFINED,
}
