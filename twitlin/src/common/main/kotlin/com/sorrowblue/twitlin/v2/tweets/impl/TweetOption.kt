package com.sorrowblue.twitlin.v2.tweets.impl

import com.sorrowblue.twitlin.objects.MediaId
import com.sorrowblue.twitlin.objects.PlaceId
import com.sorrowblue.twitlin.objects.TweetId
import com.sorrowblue.twitlin.objects.UserId
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

public object TweetOption {

    @Serializable
    public class Geo(@SerialName("place_id") public val placeId: PlaceId)

    @Serializable
    public class Media(
        @SerialName("media_ids") public val mediaIds: List<MediaId>,
        @SerialName("tagged_user_ids") public val taggedUserIds: List<UserId>? = null
    )

    @Serializable
    public class Poll(
        public val options: List<String>,
        @SerialName("duration_minutes") public val durationMinutes: Int
    )

    @Serializable
    public class Reply(
        @SerialName("in_reply_to_tweet_id") public val inReplyToTweetId: TweetId,
        @SerialName("exclude_reply_user_ids") public val excludeReplyUserIds: List<UserId>? = null
    )
}
