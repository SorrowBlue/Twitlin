/*
 * (c) 2020 SorrowBlue.
 */

package com.sorrowblue.twitlin.client

/**
 * TODO
 *
 * @param T TODO
 */
public sealed class Response<T> {

    /**
     * TODO
     *
     * @param T TODO
     * @property value TODO
     */
    public data class Success<T>(val value: T, val statusCode: Int) : Response<T>()

    /**
     * TODO
     *
     * @param T TODO
     * @property errorMessages TODO
     */
    public data class Error<T>(val errorMessages: ErrorMessages) : Response<T>()

    /**
     * TODO
     *
     * @param R TODO
     * @param onSuccess TODO
     * @param onError TODO
     * @return TODO
     */
    public inline fun <R> fold(onSuccess: (Success<T>) -> R, onError: (Error<T>) -> R): R =
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
            action.invoke(value)
        }
        return this
    }

    /**
     * TODO
     *
     * @param action TODO
     * @return TODO
     */
    public inline fun onError(action: (ErrorMessages) -> Unit): Response<T> {
        if (this is Error) {
            action.invoke(errorMessages)
        }
        return this
    }

    /**
     * TODO
     *
     * @return TODO
     */
    public fun getOrNull(): T? = when (this) {
        is Success -> value
        is Error -> null
    }

    internal companion object {
        fun <T> error(error: ErrorMessages.Error) = Error<T>(ErrorMessages(listOf(error)))
    }

}