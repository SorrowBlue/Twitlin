/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.client

import com.sorrowblue.twitlin.core.IResponse
import kotlinx.serialization.Serializable
import com.sorrowblue.twitlin.client.Error as ClientError

/**
 * TODO
 *
 * @param T TODO
 */
@Serializable(ResponseSerializer::class)
public sealed class Response<T : Any> : IResponse<T> {

    /**
     * TODO
     *
     * @param T TODO
     * @property data TODO
     */
    @Serializable
    public data class Success<T : Any>(val data: T) : Response<T>()

    /**
     * TODO
     *
     * @param T TODO
     * @property errors TODO
     */
    @Serializable
    public data class Error<T : Any>(val errors: List<ClientError>) : Response<T>()

    /**
     * TODO
     *
     * @param R TODO
     * @param onSuccess TODO
     * @param onError TODO
     * @return TODO
     */
    public inline fun <R : Any> fold(onSuccess: (Success<T>) -> R, onError: (Error<T>) -> R): R =
        when (this) {
            is Success -> onSuccess(this)
            is Error -> onError(this)
        }

    /**
     * TODO
     *
     * @param action TODO
     * @return TODO
     */
    public inline fun onSuccess(action: (T) -> Unit): Response<T> {
        if (this is Success) {
            action.invoke(data)
        }
        return this
    }

    /**
     * TODO
     *
     * @param action TODO
     * @return TODO
     */
    public inline fun onError(action: (List<ClientError>) -> Unit): Response<T> {
        if (this is Error) {
            action.invoke(errors)
        }
        return this
    }

    /**
     * TODO
     *
     * @return TODO
     */
    public fun dataOrNull(): T? = if (this is Success) data else null

    public fun <R : Any> convertData(convert: (T) -> R): Response<R> {
        return when (this) {
            is Success -> Success(convert.invoke(data))
            is Error -> Error(errors)
        }
    }

    internal companion object {
        fun <T : Any> error(error: ClientError) = Error<T>(listOf(error))
    }
}
