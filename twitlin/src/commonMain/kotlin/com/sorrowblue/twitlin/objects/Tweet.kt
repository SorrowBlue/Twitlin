package com.sorrowblue.twitlin.objects

sealed class Tweet(val source: TwitterTweet) {
	class Photo(source: TwitterTweet, val media: List<Entities.Media>) : Tweet(source)
	class Video(source: TwitterTweet, val info: Entities.Media.VideoInfo) : Tweet(source)
	class GifAnimation(source: TwitterTweet, val info: Entities.Media.VideoInfo) : Tweet(source)
	class Poll(source: TwitterTweet, val polls: List<Entities.Poll>) : Tweet(source)
	class Retweet(source: TwitterTweet, val tweet: TwitterTweet) : Tweet(source)
	class Quote(source: TwitterTweet, val tweet: TwitterTweet) : Tweet(source)
	class Card(source: TwitterTweet, val card: TwitterCard) : Tweet(source)
	class Normal(source: TwitterTweet) : Tweet(source)

	companion object {
		fun valueOf(tweet: TwitterTweet): Tweet {
			val mediaType = tweet.extendedEntities?.media?.firstOrNull()?.type
			return when {
				mediaType == Entities.Media.MediaType.PHOTO -> Photo(tweet, tweet.extendedEntities.media)
				mediaType == Entities.Media.MediaType.VIDEO
						&& tweet.extendedEntities.media.first().videoInfo != null ->
					Video(tweet, tweet.extendedEntities.media.first().videoInfo!!)
				mediaType == Entities.Media.MediaType.ANIMATED_GIF
						&& tweet.extendedEntities.media.first().videoInfo != null ->
					GifAnimation(tweet, tweet.extendedEntities.media.first().videoInfo!!)
				tweet.extendedEntities?.polls.isNullOrEmpty().not() -> Poll(tweet, tweet.extendedEntities!!.polls)
				tweet.retweetedStatus != null -> Retweet(tweet, tweet.retweetedStatus)
				tweet.quotedStatus != null -> Quote(tweet, tweet.quotedStatus)
				tweet.card != null -> Card(tweet, tweet.card)
				else -> Normal(tweet)
			}
		}
	}
}