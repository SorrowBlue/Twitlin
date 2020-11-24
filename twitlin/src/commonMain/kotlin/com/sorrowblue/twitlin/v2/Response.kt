package com.sorrowblue.twitlin.v2

import com.sorrowblue.twitlin.Parcelable
import com.sorrowblue.twitlin.Parcelize
import com.sorrowblue.twitlin.v2.objects.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class Response<T : Any> {
    inline fun onSuccess(action: (Success<T>) -> Unit): Response<T> {
        if (this is Success) {
            action(this)
        }
        return this
    }

    inline fun onFailure(action: (Failure<T>) -> Unit): Response<T> {
        if (this is Failure) {
            action(this)
        }
        return this
    }

    fun dataOrNull(): T? = if (this is Success) data else null

    inline fun <R> fold(onSuccess: (Success<T>) -> R, onFailure: (Failure<T>) -> R): R =
        when (this) {
            is Success -> onSuccess(this)
            is Failure -> onFailure(this)
        }

    @Serializable
    data class Success<T : Any>(
        val statusCode: Int = 200,
        val data: T,
        val includes: Includes? = null,
        val errors: List<Error>? = null
    ) : Response<T>()

    @Serializable
    data class Failure<T : Any>(
        val statusCode: Int = -1,
        val errors: List<Error>,
        val title: String,
        val detail: String,
        val type: String
    ) : Response<T>() {
        constructor(statusCode: Int, error: Error) : this(statusCode, listOf(error), "", "", "")
    }

}

@Serializable
data class Error(
    val title: String? = null,
    val detail: String? = null,
    val type: Type? = null,
    val id: String? = null,
    @SerialName("resource_type")
    val resourceType: String? = null,
    val field: String? = null,
    val parameter: String? = null,
    val value: String? = null,
    val section: String? = null,
    val parameters: Parameters? = null,
    val message: String? = null,
) {


    @Serializable
    enum class Type {
        /**
         * about:blank
         * A generic problem with no additional information beyond that provided by the HTTP status code.
         */
        @SerialName("Generic Problem")
        GENERIC_PROBLEM,

        /**
         * https://api.twitter.com/2/problems/invalid-request
         * A problem that indicates this request is invalid.
         */
        @SerialName("Invalid Request Problem")
        INVALID_REQUEST_PROBLEM,

        /**
         * https://api.twitter.com/2/problems/resource-not-found
         * A problem that indicates that a given Tweet, User, etc. does not exist.
         */
        @SerialName("https://api.twitter.com/2/problems/resource-not-found")
        RESOURCE_NOT_FOUND,

        /**
         * https://api.twitter.com/2/problems/not-authorized-for-resource
         * A problem that indicates you are not allowed to see a particular Tweet, User, etc.
         */
        @SerialName("Resource Unauthorized Problem")
        RESOURCE_UNAUTHORIZED_PROBLEM,

        @SerialName("https://api.twitter.com/2/problems/not-authorized-for-resource")
        NOT_AUTHORIZED_FOR_RESOURCE,

        /**
         * https://api.twitter.com/2/problems/client-forbidden
         * A problem that indicates your client is forbidden from making this request.
         */
        @SerialName("Client Forbidden Problem")
        CLIENT_FORBIDDEN_PROBLEM,

        /**
         *
         * A problem that indicates that the resource requested violates the precepts of this API.
         */
        @SerialName("https://api.twitter.com/2/problems/disallowed-resource")
        DISALLOWED_RESOURCE,

        @SerialName("https://api.twitter.com/2/problems/not-authorized-for-field")
        FIELD_AUTHORIZATION_ERROR,

        /**
         * https://api.twitter.com/2/problems/unsupported-authentication
         * A problem that indicates that the authentication used is not supported.
         */
        @SerialName("Unsupported Authentication Problem")
        UNSUPPORTED_AUTHENTICATION_PROBLEM,

        /**
         * https://api.twitter.com/2/problems/usage-capped
         * A problem that indicates that a usage cap has been exceeded.
         */
        @SerialName("Usage Capped Problem")
        USAGE_CAPPED_PROBLEM,

        /**
         * https://api.twitter.com/2/problems/streaming-connection
         * A problem that indicates something is wrong with the connection.
         */
        @SerialName("Connection Exception Problem")
        CONNECTION_EXCEPTION_PROBLEM,

        /**
         * https://api.twitter.com/2/problems/client-disconnected
         * Your client has gone away.
         */
        @SerialName("Client Disconnected Problem")
        CLIENT_DISCONNECTED_PROBLEM,

        /**
         * https://api.twitter.com/2/problems/operational-disconnect
         * You have been disconnected for operational reasons.
         */
        @SerialName("Operational Disconnect Problem")
        OPERATIONAL_DISCONNECT_PROBLEM,

        /**
         * https://api.twitter.com/2/problems/rule-cap
         * You have exceeded the maximum number of rules.
         */
        @SerialName("Rule Cap Problem")
        RILE_CAP_PROBLEM,

        /**
         * https://api.twitter.com/2/problems/invalid-rules
         * The rule you have submitted is invalid.
         */
        @SerialName("Invalid Rules Problem")
        INVALID_RULES_PROBLEM,

        /**
         * https://api.twitter.com/2/problems/duplicate-rules
         * The rule you have submitted is a duplicate.
         */
        @SerialName("Duplicate Rules Problem")
        DUPLICATE_RULES_PROBLEM,

        /**
         * UnknownHostException
         */
        @SerialName("UnknownHostException")
        UNKNOWN_HOST_EXCEPTION,

        /**
         * unknown
         */
        UNKNOWNS
    }

    @Parcelize
    @Serializable
    data class Parameters(val ids: List<String>) : Parcelable
}

@Serializable
data class Includes(
    val tweets: List<Tweet>? = null,
    val users: List<User>? = null,
    val places: List<Place>? = null,
    val media: List<Media>? = null,
    val polls: List<Poll>? = null,
)
