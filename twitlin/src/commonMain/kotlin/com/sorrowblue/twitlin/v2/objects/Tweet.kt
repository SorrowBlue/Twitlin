package com.sorrowblue.twitlin.v2.objects

import com.sorrowblue.twitlin.Parcelable
import com.sorrowblue.twitlin.Parcelize
import com.sorrowblue.twitlin.v2.serializer.DateTimeTzSerializer
import com.soywiz.klock.DateTimeTz
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Tweet(
	val id: String,
	val text: String,
	val attachments: Attachments? = null,
	@SerialName("author_id") val authorId: String? = null,
	@SerialName("context_annotations") val contextAnnotations: List<ContextAnnotation>? = null,
	@SerialName("conversation_id") val conversationId: String? = null,
	@SerialName("created_at") @Serializable(DateTimeTzSerializer::class) val createdAt: DateTimeTz? = null,
	val entities: Entities? = null,
	val geo: Geo? = null,
	@SerialName("in_reply_to_user_id") val inReplyToUserId: String? = null,
	val lang: String? = null,
	@SerialName("non_public_metrics")
	val nonPublicMetrics: NonPublicMetrics? = null,
	@SerialName("organic_metrics")
	val organicMetrics: Metrics? = null,
	@SerialName("possiby_sensitive")
	val possibySensitive: Boolean? = null,
	@SerialName("promoted_metrics")
	val promotedMetrics: Metrics? = null,
	@SerialName("public_metrics")
	val publicMetrics: PublicMetrics? = null,
	@SerialName("reference_tweets")
	val reference_tweets: ReferenceTweet? = null,
	val source: String? = null,
	val withheld: Withheld? = null,
): Parcelable

@Parcelize
@Serializable
data class Withheld(
	val copyright: Boolean,
	@SerialName("country_codes")
	val countryCodes: List<String>,
): Parcelable

@Parcelize
@Serializable
data class ReferenceTweet(
	val type: String,
	val id: String,
): Parcelable

@Parcelize
@Serializable
data class Entities(
	val annotations: List<Annotation>? = null,
	val cashtags: List<CashTag>? = null,
	val hashtags: List<Hashtag>? = null,
	val mentions: List<Mention>? = null,
	val urls: List<Url>? = null,
) : Parcelable {

	@Parcelize
	@Serializable
	data class Annotation(
		val start: Int,
		val end: Int,
		val probability: Float,
		val type: String,
		@SerialName("normalized_text")
		val normalizedText: String,
	) : Parcelable

	@Parcelize
	@Serializable
	data class CashTag(
		val start: Int,
		val end: Int,
		val tag: String,
	) : Parcelable

	@Parcelize
	@Serializable
	data class Hashtag(
		val start: Int,
		val end: Int,
		val tag: String,
	) : Parcelable

	@Parcelize
	@Serializable
	data class Mention(
		val start: Int,
		val end: Int,
		val tag: Int,
	) : Parcelable

	@Parcelize
	@Serializable
	data class Url(
		val start: Int,
		val end: Int,
		val url: String,
		val expanded_url: String,
		val display_url: String,
		val status: String,
		val title: String,
		val description: String,
		val unwound_url: String,
	) : Parcelable

}

@Parcelize
@Serializable
data class Attachments(
	@SerialName("poll_ids")
	val pollIds: List<String> = emptyList(),
	@SerialName("media_keys")
	val media_keys: List<String> = emptyList(),
) : Parcelable

@Parcelize
@Serializable
data class ContextAnnotation(
	val domain: Domain,
	val entity: Entity,
) : Parcelable {

	@Parcelize
	@Serializable
	data class Domain(
		val id: String,
		val name: String,
		val description: String,
	) : Parcelable

	@Parcelize
	@Serializable
	data class Entity(
		val id: String,
		val name: String,
	) : Parcelable
}

@Parcelize
@Serializable
data class Metrics(
	@SerialName("impression_count") val impressionCount: Int,
	@SerialName("like_count") val likeCount: Int,
	@SerialName("reply_count") val replyCount: Int,
	@SerialName("retweet_count") val retweetCount: Int,
	@SerialName("url_link_clicks") val urlLinkClicks: Int,
	@SerialName("user_profile_clicks") val userProfileClicks: Int,
) : Parcelable

@Parcelize
@Serializable
data class NonPublicMetrics(
	@SerialName("impression_count")
	val impressionCount: Int,
	@SerialName("urlLinkClicks")
	val urlLinkClicks: Int,
	@SerialName("user_profile_clicks")
	val userProfileClicks: Int,
) : Parcelable

@Parcelize
@Serializable
data class PublicMetrics(
	@SerialName("followers_count")
	val followersCount: Int,
	@SerialName("following_count")
	val followingCount: Int,
	@SerialName("tweet_count")
	val tweetCount: Int,
	@SerialName("listed_count")
	val listedCount: Int,
) : Parcelable

@Parcelize
@Serializable
data class Geo(
	val coordinates: Coordinates,
	@SerialName("place_id")
	val placeId: String,
) : Parcelable {

	@Parcelize
	@Serializable
	data class Coordinates(
		val type: String,
		val coordinates: List<Double>,
	) : Parcelable
}
