package com.sorrowblue.twitlin.v2.client

import com.sorrowblue.twitlin.client.RateLimitInfo
import com.sorrowblue.twitlin.client.TwitterResponse
import kotlin.Any
import kotlin.String
import kotlin.Unit
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import com.sorrowblue.twitlin.v2.client.Error as ClientError

/**
 * Response
 *
 * @param T
 * @constructor Create empty Response
 */
@Serializable(ResponseSerializer::class)
public sealed class Response<T : Any> : TwitterResponse {

    /**
     * Success
     *
     * @param T
     * @property data
     * @constructor Create empty Success
     */
    @Serializable
    public data class Success<T : Any> constructor(val data: T) : Response<T>() {
        @Transient
        override lateinit var rateLimitInfo: RateLimitInfo
    }

    /**
     * Error
     *
     * @param T
     * @property errors
     * @property title
     * @property detail
     * @property type
     * @property client_id
     * @property required_enrollment
     * @property registration_url
     * @property reason
     * @constructor Create empty Error
     */
    @Serializable
    public data class Error<T : Any>(
        val errors: List<ClientError>,
        val title: String? = null,
        val detail: String? = null,
        val type: ClientError.Type? = null,
        val client_id: String? = null,
        val required_enrollment: String? = null,
        val registration_url: String? = null,
        val reason: String? = null,
    ) : Response<T>() {

        public constructor(error: ClientError) : this(listOf(error))

        @Transient
        override lateinit var rateLimitInfo: RateLimitInfo
    }

    /**
     * Fold
     *
     * @param R
     * @param onSuccess
     * @param onFailure
     * @receiver
     * @receiver
     * @return
     */
    public inline fun <R> fold(onSuccess: (Success<T>) -> R, onFailure: (Error<T>) -> R): R =
        when (this) {
            is Success -> onSuccess(this)
            is Error -> onFailure(this)
        }

    /**
     * On success
     *
     * @param action
     * @receiver
     * @return
     */
    public inline fun onSuccess(action: (Success<T>) -> Unit): Response<T> {
        if (this is Success) {
            action.invoke(this)
        }
        return this
    }

    /**
     * On error
     *
     * @param action
     * @receiver
     * @return
     */
    public inline fun onError(action: (Error<T>) -> Unit): Response<T> {
        if (this is Error) {
            action.invoke(this)
        }
        return this
    }

    /**
     * Convert data
     *
     * @param R
     * @param convert
     * @receiver
     * @return
     */
    public inline fun <R : Any> convertData(convert: (T) -> R): Response<R> {
        return when (this) {
            is Success -> Success(convert.invoke(data))
            is Error -> Error(
                errors,
                title,
                detail,
                type,
                client_id,
                required_enrollment,
                registration_url,
                reason
            )
        }
    }

    /**
     * Data or null
     *
     * @return
     */
    public fun dataOrNull(): T? = if (this is Success) data else null

    /**
     * Error or null
     *
     * @return
     */
    public fun errorOrNull(): Error<T>? = if (this is Error) this else null
}
