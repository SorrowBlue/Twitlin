/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.objects

import kotlin.jvm.JvmStatic

public sealed class Tweet(public val source: TwitterTweet) {
    public class Photo(source: TwitterTweet, public val media: List<Entities.Media>) : Tweet(source)
    public class Video(source: TwitterTweet, public val info: Entities.Media.VideoInfo) :
        Tweet(source)

    public class GifAnimation(source: TwitterTweet, public val info: Entities.Media.VideoInfo) :
        Tweet(source)

    public class Poll(source: TwitterTweet, public val polls: List<Entities.Poll>) : Tweet(source)
    public class Card(source: TwitterTweet, public val card: TwitterCard) : Tweet(source)
    public class Normal(source: TwitterTweet) : Tweet(source)

    public companion object {
        @JvmStatic
        public fun valueOf(tweet: TwitterTweet): Tweet {
            return when (tweet.extendedEntities?.media?.firstOrNull()?.type) {
                Entities.Media.MediaType.PHOTO -> Photo(tweet, tweet.extendedEntities.media)
                Entities.Media.MediaType.VIDEO ->
                    Video(tweet, tweet.extendedEntities.media.first().videoInfo!!)
                Entities.Media.MediaType.ANIMATED_GIF ->
                    GifAnimation(tweet, tweet.extendedEntities.media.first().videoInfo!!)
                else -> if (tweet.entities?.polls.isNullOrEmpty().not()) Poll(
                    tweet,
                    tweet.entities?.polls!!
                )
                else tweet.card?.let { Card(tweet, it) } ?: Normal(tweet)
            }
        }
    }
}
