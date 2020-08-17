package com.sorrowblue.twitlin.v2

import com.sorrowblue.twitlin.v2.objects.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class Response<T : Any> {
	fun onSuccess(action: (Success<T>) -> Unit): Response<T> {
		if (this is Success) {
			action(this)
		}
		return this
	}

	fun onFailure(action: (Failure<T>) -> Unit): Response<T> {
		if (this is Failure) {
			action(this)
		}
		return this
	}

	fun dataOrNull(): T? = if (this is Success) data else null

	@Serializable
	data class Success<T : Any>(
		val statusCode: Int = 200,
		val data: T,
		val includes: Includes? = null,
		val errors: List<Error>? = null,
	) : Response<T>()

	@Serializable
	data class Failure<T : Any>(val statusCode: Int = -1, val errors: List<Error>) : Response<T>()

}

@Serializable
data class Error(
	val title: String,
	val detail: String,
	val type: String,
	@SerialName("resource_type")
	val resourceType: String? = null,
	val field: String? = null,
	val parameter: String? = null,
	val value: String? = null,
	val section: String? = null,
)

@Serializable
data class Includes(
	val tweets: List<Tweet>? = null,
	val users: List<User>? = null,
	val places: List<Place>? = null,
	val media: List<Media>? = null,
	val polls: List<Poll>? = null,
)
