package com.sorrowblue.twitlin.v2.tweets

import com.sorrowblue.twitlin.v2.Response
import com.sorrowblue.twitlin.v2.objects.Tweet

interface TweetsApi {

	suspend fun tweets(
		id: String,
		expansions: List<Expansion>? = null,
		mediaFields: List<MediaField>? = null,
		placeFields: List<PlaceField>? = null,
		pollFields: List<PollField>? = null,
		tweetFields: List<TweetField>? = null,
		userFields: List<UserField>? = null,
	): Response<Tweet>

	suspend fun tweets(
		ids: List<String>,
		expansions: List<Expansion>? = null,
		mediaFields: List<MediaField>? = null,
		placeFields: List<PlaceField>? = null,
		pollFields: List<PollField>? = null,
		tweetFields: List<TweetField>? = null,
		userFields: List<UserField>? = null,
	): Response<List<Tweet>>
}
