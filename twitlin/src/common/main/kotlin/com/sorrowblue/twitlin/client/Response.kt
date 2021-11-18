package com.sorrowblue.twitlin.client

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import com.sorrowblue.twitlin.client.Error as ClientError

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
    public data class Success<T : Any>(val data: T) : Response<T>() {
        @Transient
        override lateinit var rateLimitInfo: RateLimitInfo
    }

    /**
     * Error
     *
     * @param T
     * @property errors
     * @constructor Create empty Error
     */
    @Serializable
    public data class Error<T : Any>(val errors: List<ClientError>) : Response<T>() {
        @Transient
        override lateinit var rateLimitInfo: RateLimitInfo
    }

    /**
     * Fold
     *
     * @param R
     * @param onSuccess
     * @param onError
     * @receiver
     * @receiver
     * @return
     */
    public inline fun <R : Any> fold(onSuccess: (Success<T>) -> R, onError: (Error<T>) -> R): R =
        when (this) {
            is Success -> onSuccess(this)
            is Error -> onError(this)
        }

    /**
     * On success
     *
     * @param action
     * @receiver
     * @return
     */
    public inline fun onSuccess(action: (T) -> Unit): Response<T> {
        if (this is Success) {
            action.invoke(data)
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
    public inline fun onError(action: (List<ClientError>) -> Unit): Response<T> {
        if (this is Error) {
            action.invoke(errors)
        }
        return this
    }

    /**
     * Data or null
     *
     * @return
     */
    public fun dataOrNull(): T? = if (this is Success) data else null

    /**
     * Convert data
     *
     * @param R
     * @param convert
     * @receiver
     * @return
     */
    public fun <R : Any> convertData(convert: (T) -> R): Response<R> {
        return when (this) {
            is Success -> Success(convert.invoke(data)).also {
                it.rateLimitInfo = rateLimitInfo
            }
            is Error -> Error<R>(errors).also {
                it.rateLimitInfo = rateLimitInfo
            }
        }
    }

    internal companion object {

        /**
         * Error
         *
         * @param T
         * @param error
         */
        fun <T : Any> error(error: ClientError) = Error<T>(listOf(error))
    }
}
