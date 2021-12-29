package com.sorrowblue.twitlin.v2.client

import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(ProblemSerializer::class)
public sealed class Problem {
    public abstract val title: String
    public abstract val type: ProblemType
    public abstract val detail: String
}

/**
 * A generic problem with no additional information beyond that provided by the HTTP status code.
 *
 * @property title
 * @property status
 */
@Serializable
public class GenericProblem(
    override val title: String,
    override val detail: String,
    public val status: Int
) : Problem() {
    @Required
    override val type: ProblemType = ProblemType.GENERIC
}

/**
 * A problem that indicates this request is invalid.
 *
 * @property title
 * @property errors
 */
@Serializable
public class InvalidRequestProblem(
    override val title: String,
    override val detail: String,
    public val errors: List<Error>
) : Problem() {
    @Required
    override val type: ProblemType = ProblemType.INVALID_REQUEST

    @Serializable
    public class Error(public val parameters: Map<String, List<String>>, public val message: String)
}

/**
 * A problem that indicates that a given Tweet, User, etc. does not exist.
 *
 * @property title
 * @property parameter
 * @property value
 * @property resourceId
 * @property resourceType
 */
@Serializable
public class ResourceNotFoundProblem(
    override val title: String,
    override val detail: String,
    public val parameter: String,
    public val value: String,
    @SerialName("resource_id") public val resourceId: String,
    @SerialName("resource_type") public val resourceType: ResourceType,
) : Problem() {
    override val type: ProblemType = ProblemType.RESOURCE_NOT_FOUND
}

@Serializable
public enum class ResourceType {
    @SerialName("user")
    USER,

    @SerialName("tweet")
    TWEET,

    @SerialName("media")
    MEDIA
}

@Serializable
public enum class Section {
    @SerialName("data")
    DATA,

    @SerialName("include")
    INCLUDES
}

/**
 * A problem that indicates you are not allowed to see a particular Tweet, User, etc.
 *
 * @property title
 * @property value
 * @property parameter
 * @property section
 * @property resourceId
 * @property resourceType
 */
@Serializable
public class ResourceUnauthorizedProblem(
    override val title: String,
    override val detail: String,
    public val value: String,
    public val parameter: String,
    public val section: Section,
    @SerialName("resource_id") public val resourceId: String,
    @SerialName("resource_type") public val resourceType: ResourceType
) : Problem() {
    override val type: ProblemType = ProblemType.NOT_AUTHORIZED_FOR_RESOURCE
}

/**
 * A problem that indicates a particular Tweet, User, etc. is not available to you.
 *
 * @property title
 * @property parameter
 * @property resourceId
 * @property resourceType
 */
@Serializable
public class ResourceUnavailableProblem(
    override val title: String,
    override val detail: String,
    public val parameter: String,
    @SerialName("resource_id") public val resourceId: String,
    @SerialName("resource_type") public val resourceType: ResourceType
) : Problem() {
    override val type: ProblemType = ProblemType.RESOURCE_UNAVAILABLE
}

/**
 * A problem that indicates that you are not allowed to see a particular field on a Tweet, User, etc.
 *
 * @property title
 * @property type
 * @property section
 * @property resourceType
 * @property field
 */
@Serializable
public class FieldUnauthorizedProblem(
    override val title: String,
    override val detail: String,
    public val section: Section,
    @SerialName("resource_type") public val resourceType: ResourceType,
    public val field: String,
    public val parameter: String,
    @SerialName("resource_id") public val resourceId: String,
    public val value: String
) : Problem() {
    override val type: ProblemType = ProblemType.FIELD_UNAUTHORIZED
}

/**
 * A problem that indicates your client is forbidden from making this request.
 *
 * @property title
 * @property reason
 * @property registration_url
 */
@Serializable
public class ClientForbiddenProblem(
    override val title: String,
    override val detail: String,
    public val reason: Reason,
    public val registration_url: String
) : Problem() {
    override val type: ProblemType = ProblemType.CLIENT_FORBIDDEN
}

@Serializable
public enum class Reason {
    @SerialName("official-client-forbidden")
    OFFICIAL_CLIENT_FORBIDDEN,

    @SerialName("client-not-enrolled")
    CLIENT_NOT_ENROLLED
}

/**
 * A problem that indicates that the resource requested violates the precepts of this API.
 *
 * @property title
 * @property type
 * @property resourceId
 * @property resourceType
 * @property section
 */
@Serializable
public class DisallowedResourceProblem(
    override val title: String,
    override val detail: String,
    @SerialName("resource_id") public val resourceId: String,
    @SerialName("resource_type") public val resourceType: ResourceType,
    public val section: Section,
) : Problem() {
    override val type: ProblemType = ProblemType.DISALLOWED_RESOURCE
}

/**
 * A problem that indicates that the authentication used is not supported.
 *
 * @property title
 * @property type
 */
@Serializable
public class UnsupportedAuthenticationProblem(
    override val title: String,
    override val detail: String,
    public val status: Int
) : Problem() {
    override val type: ProblemType = ProblemType.UNSUPPORTED_AUTHENTICATION
}

/**
 * A problem that indicates that a usage cap has been exceeded.
 *
 * @property title
 * @property type
 * @property period
 * @property scope
 */
public class UsageCapExceededProblem(
    override val title: String,
    override val detail: String,
    public val period: Period,
    public val scope: Scope
) : Problem() {
    override val type: ProblemType = ProblemType.USAGE_CAPPED
}

@Serializable
public enum class Period {
    @SerialName("Daily")
    DAILY,

    @SerialName("Monthly")
    MONTHLY
}

@Serializable
public enum class Scope {
    @SerialName("Account")
    ACCOUNT,

    @SerialName("Product")
    PRODUCT
}

/**
 * A problem that indicates something is wrong with the connection
 *
 * @property title
 * @property type
 * @property connection_issue
 */
@Serializable
public class ConnectionExceptionProblem(
    override val title: String,
    override val detail: String,
    public val connection_issue: ConnectionIssue
) : Problem() {
    override val type: ProblemType = ProblemType.STREAMING_CONNECTION
}

@Serializable
public enum class ConnectionIssue {
    @SerialName("TooManyConnections")
    TOO_MANY_CONNECTIONS,

    @SerialName("ProvisioningSubscription")
    PROVISIONING_SUBSCRIPTION,

    @SerialName("RuleConfigurationIssue")
    RULE_CONFIGURATION_ISSUE,

    @SerialName("RulesInvalidIssue")
    RULES_INVALID_ISSUE,
}

/**
 * Your client has gone away.
 *
 * @property title
 * @property type
 */
public class ClientDisconnectedProblem(override val title: String, override val detail: String) : Problem() {
    override val type: ProblemType = ProblemType.CLIENT_DISCONNECTED
}

/**
 * You have been disconnected for operational reasons.
 *
 * @property title
 * @property type
 * @property disconnectType
 */
public class OperationalDisconnectProblem(
    override val title: String,
    override val detail: String,
    @SerialName("disconnect_type") public val disconnectType: DisconnectType
) : Problem() {
    override val type: ProblemType = ProblemType.OPERATIONAL_DISCONNECT
}

public enum class DisconnectType {
// "OperationalDisconnect", "UpstreamOperationalDisconnect", "ForceDisconnect", "UpstreamUncleanDisconnect", "SlowReader", "InternalError", "ClientApplicationStateDegraded", "InvalidRules"
}

/**
 * You have exceeded the maximum number of rules.
 *
 * @property title
 */
public class RulesCapProblem(override val title: String, override val detail: String) : Problem() {
    override val type: ProblemType = ProblemType.RULE_CAP
}

/**
 * The rule you have submitted is invalid.
 *
 * @property title
 * @property type
 * @property disconnectType
 */
public class InvalidRuleProblem(
    override val title: String,
    override val detail: String,
    @SerialName("disconnect_ty pe") public val disconnectType: DisconnectType
) : Problem() {
    override val type: ProblemType = ProblemType.INVALID_RULES
}

/**
 * The rule you have submitted is a duplicate.
 *
 * @property title
 * @constructor Create empty Duplicate rule problem
 */
public class DuplicateRuleProblem(override val title: String, override val detail: String) : Problem() {
    override val type: ProblemType = ProblemType.DUPLICATE_RULES
}

/**
 * You cannot create a new job if one is already in progress.
 *
 * @property title
 */
public class ConflictProblem(override val title: String, override val detail: String) : Problem() {
    override val type: ProblemType = ProblemType.CONFLICT
}

/**
 * A problem that indicates the user's rule set is not compliant.
 *
 * @property title
 */
public class NonCompliantRulesProblem(override val title: String, override val detail: String) : Problem() {
    override val type: ProblemType = ProblemType.NONCOMPLIANT_RULES
}


/**
 * A problem that indicates the user's rule set is not compliant.
 *
 * @property title
 */
public class UndefinedProblem : Problem() {
    override val title: String = "undefined"
    override val detail: String = ""
    override val type: ProblemType = ProblemType.UNDEFINED
}

