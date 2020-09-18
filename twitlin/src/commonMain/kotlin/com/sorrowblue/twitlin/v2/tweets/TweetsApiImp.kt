package com.sorrowblue.twitlin.v2.tweets

import com.sorrowblue.twitlin.net.Urls
import com.sorrowblue.twitlin.v2.Client
import com.sorrowblue.twitlin.v2.Response
import com.sorrowblue.twitlin.v2.objects.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDateTime

private const val TWEETS = "${Urls._2}/tweets"

internal class TweetsApiImp(private val client: Client) : TweetsApi {

	override suspend fun tweets(
		id: String,
		expansions: List<Expansion>?,
		mediaFields: List<MediaField>?,
		placeFields: List<PlaceField>?,
		pollFields: List<PollField>?,
		tweetFields: List<TweetField>?,
		userFields: List<UserField>?,
	): Response<Tweet> = client.get(
		"$TWEETS/$id",
		"expansions" to expansions?.toParameter(),
		"media.fields" to mediaFields?.toParameter(),
		"place.fields" to placeFields?.toParameter(),
		"poll.fields" to pollFields?.toParameter(),
		"tweet.fields" to tweetFields?.toParameter(),
		"user.fields" to userFields?.toParameter(),
	)

	override suspend fun tweets(
		ids: List<String>,
		expansions: List<Expansion>?,
		mediaFields: List<MediaField>?,
		placeFields: List<PlaceField>?,
		pollFields: List<PollField>?,
		tweetFields: List<TweetField>?,
		userFields: List<UserField>?,
	): Response<List<Tweet>> = client.get(
		TWEETS,
		"ids" to ids.joinToString(","),
		"expansions" to expansions?.toParameter(),
		"media.fields" to mediaFields?.toParameter(),
		"place.fields" to placeFields?.toParameter(),
		"poll.fields" to pollFields?.toParameter(),
		"tweet.fields" to tweetFields?.toParameter(),
		"user.fields" to userFields?.toParameter(),
	)

	override suspend fun hidden(id: String, isHidden: Boolean): Response<Hidden> =
		client.put("$TWEETS/$id/hidden", Hidden(isHidden))

	override suspend fun searchRecent(
		query: String, maxResults: Int?, nextToken: String?,
		sinceId: String?, startTime: LocalDateTime?, untilId: String?, endTime: LocalDateTime?,
		expansions: List<Expansion>?, mediaFields: List<MediaField>?,
		placeFields: List<PlaceField>?, pollFields: List<PollField>?,
		tweetFields: List<TweetField>?, userFields: List<UserField>?
	): Response<Tweet> = client.get(
		"$TWEETS/search/recent",
		"query" to query, "max_results" to maxResults, "next_token" to nextToken,
		"since_id" to sinceId, "until_id" to untilId,
		"start_time" to startTime, "end_time" to endTime,
		"expansions" to expansions?.toParameter(), "media.fields" to mediaFields?.toParameter(),
		"place.fields" to placeFields?.toParameter(),
		"poll.fields" to pollFields?.toParameter(),
		"tweet.fields" to tweetFields?.toParameter(), "user.fields" to userFields?.toParameter()
	)

	@ExperimentalCoroutinesApi
	override fun sampleStream(
		expansions: List<Expansion>?,
		mediaFields: List<MediaField>?,
		placeFields: List<PlaceField>?,
		pollFields: List<PollField>?,
		tweetFields: List<TweetField>?,
		userFields: List<UserField>?
	): Flow<Response<Tweet>> = client.streaming(
		"$TWEETS/sample/stream",
		"expansions" to expansions?.toParameter(),
		"media.fields" to mediaFields?.toParameter(),
		"place.fields" to placeFields?.toParameter(),
		"poll.fields" to pollFields?.toParameter(),
		"tweet.fields" to tweetFields?.toParameter(),
		"user.fields" to userFields?.toParameter(),
		useBearerToken = true
	)

	//
	@ExperimentalCoroutinesApi
	override fun searchStream(
		expansions: List<Expansion>?,
		mediaFields: List<MediaField>?,
		placeFields: List<PlaceField>?,
		pollFields: List<PollField>?,
		tweetFields: List<TweetField>?,
		userFields: List<UserField>?
	): Flow<Response<Tweet>> = client.streaming(
		"$TWEETS/search/stream",
		"expansions" to expansions?.toParameter(),
		"media.fields" to mediaFields?.toParameter(),
		"place.fields" to placeFields?.toParameter(),
		"poll.fields" to pollFields?.toParameter(),
		"tweet.fields" to tweetFields?.toParameter(),
		"user.fields" to userFields?.toParameter(),
		useBearerToken = true
	)

	override suspend fun searchStreamRules(ids: List<String>?): Response<SearchStreamRule> =
		client.getCustom<SearchStreamRule, SearchStreamRuleResponse>(
			"$TWEETS/search/stream/rules",
			"ids" to ids?.joinToString(","),
			useBearerToken = true
		) { res, httpRes -> res.toSuccess(httpRes.status.value) }

	override suspend fun addSearchStreamRules(
		rules: List<AddSearchStreamRule>, dryRun: Boolean?
	): Response<AddSearchStreamRuleResult> = client.postJsonCustom(
		"$TWEETS/search/stream/rules", "dry_run" to dryRun,
		body = AddSearchStreamRuleRequest(rules),
		useBearerToken = true
	) { res: AddSearchStreamRuleResponse, httpRes -> res.toSuccess(httpRes.status.value) }

	override suspend fun deleteSearchStreamRules(
		ids: List<String>, dryRun: Boolean?
	): Response<DeleteSearchStreamRuleResult> = client.postJsonCustom(
		"$TWEETS/search/stream/rules", "dry_run" to dryRun,
		body = DeleteSearchStreamRuleRequest(ids),
		useBearerToken = true
	) { res: DeleteSearchStreamRuleResponse, httpRes -> res.toSuccess(httpRes.status.value) }
}

fun List<Field>.toParameter() = joinToString(",") { it.value }

interface Field {
	val value: String
}

enum class Expansion(override val value: String) : Field {
	AUTHOR_ID("author_id"),
	REFERENCED_TWEETS_ID("referenced_tweets.id"),
	IN_REPLY_TO_USER_ID("in_reply_to_user_id"),
	ATTACHMENTS_MEDIA_KEYS("attachments.media_keys"),
	ATTACHMENTS_POLL_IDS("attachments.poll_ids"),
	GEO_PLACE_ID("geo.place_id"),
	ENTITIES_MENTIONS_USERNAME("entities.mentions.username"),
	REFERENCED_TWEETS_ID_AUTHOR_ID("referenced_tweets.id.author_id"),
	;

	companion object {
		fun all() = listOf(
			AUTHOR_ID,
			REFERENCED_TWEETS_ID,
			IN_REPLY_TO_USER_ID,
			ATTACHMENTS_MEDIA_KEYS,
			ATTACHMENTS_POLL_IDS,
			GEO_PLACE_ID,
			ENTITIES_MENTIONS_USERNAME,
			REFERENCED_TWEETS_ID_AUTHOR_ID,
		)
	}
}

enum class MediaField(override val value: String) : Field {
	DURATION_MS("duration_ms"),
	HEIGHT("height"),
	MEDIA_KEY("media_key"),
	PREVIEW_IMAGE_URL("preview_image_url"),
	TYPE("type"),
	URL("url"),
	WIDTH("width"),
	PUBLIC_METRICS("public_metrics"),
	NON_PUBLIC_METRICS("non_public_metrics"),
	ORGANIC_METRICS("organic_metrics"),
	PROMOTED_METRICS("promoted_metrics"),
	;

	companion object {
		fun all() = listOf(
			DURATION_MS, HEIGHT,
			MEDIA_KEY,
			PREVIEW_IMAGE_URL,
			TYPE,
			URL,
			WIDTH,
			PUBLIC_METRICS,
			NON_PUBLIC_METRICS,
			ORGANIC_METRICS,
			PROMOTED_METRICS,
		)
	}
}

enum class PlaceField(override val value: String) : Field {
	CONTAINED_WITHIN("contained_within"),
	COOUNTRY("country"),
	COUNTRY_CODE("country_code"),
	FULL_NAME("full_name"),
	GEO("geo"),
	ID("id"),
	NAME("name"),
	PLACE_TYPE("place_type"),
	;

	companion object {
		fun all() = listOf(
			CONTAINED_WITHIN,
			COOUNTRY,
			COUNTRY_CODE,
			FULL_NAME,
			GEO,
			ID,
			NAME,
			PLACE_TYPE,
		)
	}
}

enum class PollField(override val value: String) : Field {
	DURATION_MINUTES("duration_minutes"),
	END_DATETIME("end_datetime"),
	ID("id"),
	OPTIONS("options"),
	VOTING_STATUS("voting_status"),
	;

	companion object {
		fun all() = listOf(
			DURATION_MINUTES,
			END_DATETIME,
			ID,
			OPTIONS,
			VOTING_STATUS,
		)
	}
}

enum class TweetField(override val value: String) : Field {
	ATTACHMENTS("attachments"),
	AUTHOR_ID("author_id"),
	CONTEXT_ANNOTATIONS("context_annotations"),
	CONVERSATION_ID("conversation_id"),
	CREATED_AT("created_at"),
	ENTITIES("entities"),
	GEO("geo"),
	ID("id"),
	IN_REPLY_TO_USER_ID("in_reply_to_user_id"),
	LANG("lang"),
	NON_PUBLIC_METRICS("non_public_metrics"),
	PUBLIC_METRICS("public_metrics"),
	ORGANIC_METRICS("organic_metrics"),
	PROMOTED_METRICS("promoted_metrics"),
	POSSIBLY_SENSITIVE("possibly_sensitive"),
	REFERENCED_TWEETS("referenced_tweets"),
	SOURCE("source"),
	TEXT("text"),
	WITHHELD("withheld"),
	;

	companion object {
		fun all() = listOf(
			ATTACHMENTS,
			AUTHOR_ID,
			CONTEXT_ANNOTATIONS,
			CONVERSATION_ID,
			CREATED_AT,
			ENTITIES,
			GEO,
			ID,
			IN_REPLY_TO_USER_ID,
			LANG,
			NON_PUBLIC_METRICS,
			PUBLIC_METRICS,
			ORGANIC_METRICS,
			PROMOTED_METRICS,
			POSSIBLY_SENSITIVE,
			REFERENCED_TWEETS,
			SOURCE,
			TEXT,
			WITHHELD,
		)
	}
}

enum class UserField(override val value: String) : Field {
	CREATED_AT("created_at"),
	DESCRIPTION("description"),
	ENTITIES("entities"),
	ID("id"),
	LOCATION("location"),
	NAME("name"),
	PINNED_TWEET_ID("pinned_tweet_id"),
	PROFILE_IMAGE_URL("profile_image_url"),
	PROTECTED("protected"),
	PUBLIC_METRICS("public_metrics"),
	URL("url"),
	USERNAME("username"),
	VERIFIED("verified"),
	WITHHELD("withheld"),
	;

	companion object {
		fun all() = listOf(
			CREATED_AT,
			DESCRIPTION,
			ENTITIES,
			ID,
			LOCATION,
			NAME,
			PINNED_TWEET_ID,
			PROFILE_IMAGE_URL,
			PROTECTED,
			PUBLIC_METRICS,
			URL,
			USERNAME,
			VERIFIED,
			WITHHELD
		)
	}
}
