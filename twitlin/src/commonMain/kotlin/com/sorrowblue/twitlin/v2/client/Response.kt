/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.client

import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalDateTimeISOSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.sorrowblue.twitlin.v2.client.Error as ClientError

/**
 * TODO
 *
 * @param T
 */
@Serializable(ResponseSerializer::class)
public sealed class Response<T : Any> : JvmSerializable {

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
        val type: String? = null
    ) : Response<T>(),
        JvmSerializable {

        public constructor(error: ClientError) : this(listOf(error), "", "", "")
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
            is Error -> Error(errors, title, detail, type)
        }
    }

    /**
     * TODO
     *
     * @return
     */
    public fun dataOrNull(): T? = if (this is Success) data else null
}

@Serializable
public sealed class Meta {

    @Serializable
    public object Empty : Meta()

    @Serializable
    public data class Paging(
        @SerialName("oldest_id")
        val oldestId: String,
        @SerialName("newest_id")
        val newestId: String,
        @SerialName("result_count")
        val resultCount: Int,
        @SerialName("next_token")
        val nextToken: String
    ) : Meta()

    @Serializable
    public data class InvalidRule(
        @Serializable(LocalDateTimeISOSerializer::class)
        val sent: LocalDateTime,
        val summary: Summary
    ) : Meta() {

        @Serializable
        public data class Summary(
            val created: Int,
            @SerialName("not_created")
            val notCreated: Int,
            val valid: Int,
            val invalid: Int
        )
    }
}
