package com.sorrowblue.twitlin.objects

import kotlin.jvm.JvmStatic

sealed class Tweet(val source: TwitterTweet) {
	class Photo(source: TwitterTweet, val media: List<Entities.Media>) : Tweet(source)
	class Video(source: TwitterTweet, val info: Entities.Media.VideoInfo) : Tweet(source)
	class GifAnimation(source: TwitterTweet, val info: Entities.Media.VideoInfo) : Tweet(source)
	class Poll(source: TwitterTweet, val polls: List<Entities.Poll>) : Tweet(source)
	class Card(source: TwitterTweet, val card: TwitterCard) : Tweet(source)
	class Normal(source: TwitterTweet) : Tweet(source)

	companion object {
		@JvmStatic
		fun valueOf(tweet: TwitterTweet): Tweet {
			return when (tweet.extendedEntities?.media?.firstOrNull()?.type) {
				Entities.Media.MediaType.PHOTO -> Photo(tweet, tweet.extendedEntities.media)
				Entities.Media.MediaType.VIDEO ->
					Video(tweet, tweet.extendedEntities.media.first().videoInfo!!)
				Entities.Media.MediaType.ANIMATED_GIF ->
					GifAnimation(tweet, tweet.extendedEntities.media.first().videoInfo!!)
				else -> if (tweet.entities?.polls.isNullOrEmpty().not()) Poll(tweet, tweet.entities?.polls!!)
				else tweet.card?.let { Card(tweet, it) } ?: Normal(tweet)
			}
		}
	}
}