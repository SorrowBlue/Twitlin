package com.sorrowblue.twitlin.net

import kotlinx.serialization.Serializable

sealed class Response<T> {

	companion object {
		internal fun <T> success(value: T): SUCCESS<T> = SUCCESS(value)
		internal fun <T> error(errors: List<ErrorMessages.Error>): Error<T> = Error(errors)
		internal fun <T> error(vararg errors: ErrorMessages.Error): Error<T> = Error(errors.asList())
	}

	inline fun <R> fold(onSuccess: (T) -> R, onError: (List<ErrorMessages.Error>) -> R): R =
		when (this) {
			is SUCCESS -> onSuccess(value)
			is Error -> onError(errors)
		}

	inline fun onSuccess(action: (T) -> Unit): Response<T> {
		if (this is SUCCESS) {
			action.invoke(value)
		}
		return this
	}

	fun <R> changeSuccess(action: (T) -> R): Response<R> = when (this) {
		is SUCCESS -> success(action.invoke(value))
		is Error -> error(errors)
	}

	inline fun onError(action: (List<ErrorMessages.Error>) -> Unit): Response<T> {
		if (this is Error) {
			action.invoke(errors)
		}
		return this
	}

	fun getOrNull(): T? = when (this) {
		is SUCCESS -> value
		is Error -> null
	}

	class SUCCESS<T> internal constructor(val value: T) : Response<T>()

	class Error<T> internal constructor(val errors: List<ErrorMessages.Error>) : Response<T>()
}

@Serializable
data class ErrorMessages(
	val errors: List<Error> = emptyList(),
) {
	@Serializable
	data class Error(
		val message: String = "",
		val code: Int = 0,
	)
}
