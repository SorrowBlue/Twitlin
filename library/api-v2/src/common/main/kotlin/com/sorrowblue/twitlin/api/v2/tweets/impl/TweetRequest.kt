package com.sorrowblue.twitlin.api.v2.tweets.impl

import com.sorrowblue.twitlin.api.v2.objects.Tweet
import com.sorrowblue.twitlin.core.objects.TweetId
import kotlinx.serialization.Serializable

@Serializable
internal class TweetRequest(
    val direct_message_deep_link: String? = null,
    val for_super_followers_only: Boolean,
    val geo: TweetOption.Geo? = null,
    val media: TweetOption.Media? = null,
    val poll: TweetOption.Poll? = null,
    val quote_tweet_id: TweetId? = null,
    val reply: TweetOption.Reply? = null,
    val reply_settings: Tweet.ReplySettings? = null,
    val text: String? = null
)
