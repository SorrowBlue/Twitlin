package com.sorrowblue.twitlin.v2

import com.sorrowblue.twitlin.Parcelable
import com.sorrowblue.twitlin.Parcelize
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
	data class Failure<T : Any>(
		val statusCode: Int = -1,
		val errors: List<Error>,
		val title: String,
		val detail: String,
		val type: String
	) : Response<T>() {
		constructor(statusCode: Int, error: Error) : this(statusCode, listOf(error), "", "", "")
	}

}

@Serializable
data class Error(
	val title: String? = null,
	val detail: String? = null,
	val type: String? = null,
	val id: String? = null,
	@SerialName("resource_type")
	val resourceType: String? = null,
	val field: String? = null,
	val parameter: String? = null,
	val value: String? = null,
	val section: String? = null,
	val parameters: Parameters? = null,
	val message: String? = null,
) {

	@Parcelize
	@Serializable
	data class Parameters(val ids: List<String>) : Parcelable
}

@Serializable
data class Includes(
	val tweets: List<Tweet>? = null,
	val users: List<User>? = null,
	val places: List<Place>? = null,
	val media: List<Media>? = null,
	val polls: List<Poll>? = null,
)

//@Serializable
//data class AddedStreamRule(
//	val data: List<StreamRule> = emptyList(),
//	val meta: Meta
//) {
//
//	@Serializable
//	data class Meta(
//		@Serializable(LocalDateTimeSerializer::class)
//		val sent: LocalDateTime,
//		val summary: Summary,
//		val errors: List<Error> = emptyList()
//	) {
//
//		@Serializable
//		data class Summary(
//			val created: Int,
//			@SerialName("not_created") val notCreated: Int
//		)
//	}
//}
//
//@Serializable
//data class DeletedStreamRule(
//	val data: List<StreamRule>? = emptyList(),
//	val meta: Meta,
//	val errors: List<Error> = emptyList()
//) {
//
//	@Serializable
//	data class Meta(
//		@Serializable(LocalDateTimeSerializer::class)
//		val sent: LocalDateTime,
//		val summary: Summary
//	) {
//
//		@Serializable
//		data class Summary(
//			val deleted: Int,
//			@SerialName("not_deleted") val notDeleted: Int
//		)
//	}
//}
