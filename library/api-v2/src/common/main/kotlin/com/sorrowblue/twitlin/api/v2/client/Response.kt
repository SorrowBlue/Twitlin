package com.sorrowblue.twitlin.api.v2.client

import com.sorrowblue.twitlin.core.client.RateLimitInfo
import com.sorrowblue.twitlin.core.client.TwitterResponse
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

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
     */
    @Serializable
    public data class Error<T : Any> constructor(
        val errors: List<Problem>
    ) : Response<T>() {

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
        return when (val res = this) {
            is Success -> Success(convert.invoke(res.data)).apply {
                rateLimitInfo = res.rateLimitInfo
            }
            is Error -> Error<R>(res.errors).apply {
                rateLimitInfo = res.rateLimitInfo
            }
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
