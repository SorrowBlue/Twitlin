package com.sorrowblue.twitlin.net

import kotlinx.serialization.Serializable

sealed class Response<T> {

	companion object {
		fun <T> success(value: T): SUCCESS<T> = SUCCESS(value)
		fun <T> error(errors: List<ErrorMessages.Error>): Error<T> = Error(errors)
		fun <T> error(errorMessages: ErrorMessages): Error<T> = error(errorMessages.errors)
		fun <T> error(vararg errors: ErrorMessages.Error): Error<T> = Error(errors.asList())
		fun <T> error(message: String, code: Int): Error<T> =
			error(ErrorMessages.Error(message, code))
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
