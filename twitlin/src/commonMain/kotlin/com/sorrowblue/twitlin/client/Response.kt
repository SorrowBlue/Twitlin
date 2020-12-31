/*
 * (c) 2020.
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
    public class Success<T>(public val value: T) : Response<T>()

    /**
     * TODO
     *
     * @param T TODO
     * @property errorMessages TODO
     */
    public class Error<T>(public val errorMessages: ErrorMessages) : Response<T>()

    /**
     * TODO
     *
     * @param R TODO
     * @param onSuccess TODO
     * @param onError TODO
     * @return TODO
     */
    public inline fun <R> fold(onSuccess: (T) -> R, onError: (ErrorMessages) -> R): R =
        when (this) {
            is Success -> onSuccess(value)
            is Error -> onError(errorMessages)
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
