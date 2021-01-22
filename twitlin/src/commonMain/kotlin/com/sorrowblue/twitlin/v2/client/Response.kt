/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.client

import com.sorrowblue.twitlin.v2.client.Error as ClientError
import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.core.IResponse
import kotlinx.serialization.Serializable

/**
 * TODO
 *
 * @param T
 */
@Serializable(ResponseSerializer::class)
public sealed class Response<T : Any> : IResponse<T>, JvmSerializable {

    /**
     * TODO
     *
     * @param T
     * @property data
     */
    @Serializable(ResponseSerializer::class)
    public data class Success<T : Any> constructor(val data: T) : Response<T>(), JvmSerializable

    /**
     * TODO
     *
     * @param T
     * @property errors
     */
    @Serializable(ResponseSerializer::class)
    public data class Error<T : Any>(
        val errors: List<ClientError>,
        val title: String? = null,
        val detail: String? = null,
        val type: ClientError.Type? = null,
        val client_id: String? = null,
        val required_enrollment: String? = null,
        val registration_url: String? = null,
        val reason: String? = null,
    ) : Response<T>(),
        JvmSerializable {

        public constructor(error: ClientError) : this(listOf(error))
    }

    /**
     * TODO
     *
     * @param R
     * @param onSuccess
     * @param onFailure
     * @return
     */
    public inline fun <R> fold(onSuccess: (Success<T>) -> R, onFailure: (Error<T>) -> R): R =
        when (this) {
            is Success -> onSuccess(this)
            is Error -> onFailure(this)
        }

    /**
     * TODO
     *
     * @param action
     * @return
     */
    public inline fun onSuccess(action: (Success<T>) -> Unit): Response<T> {
        if (this is Success) {
            action.invoke(this)
        }
        return this
    }

    /**
     * TODO
     *
     * @param action
     * @return
     */
    public inline fun onError(action: (Error<T>) -> Unit): Response<T> {
        if (this is Error) {
            action.invoke(this)
        }
        return this
    }

    /**
     * TODO
     *
     * @param R
     * @param convert
     * @return
     */
    public fun <R : Any> convertData(convert: (T) -> R): Response<R> {
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
     * TODO
     *
     * @return
     */
    public fun dataOrNull(): T? = if (this is Success) data else null

    public fun errorOrNull(): Error<T>? = if (this is Error) this else null
}
