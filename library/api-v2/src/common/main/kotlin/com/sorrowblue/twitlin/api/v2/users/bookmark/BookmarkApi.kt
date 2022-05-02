package com.sorrowblue.twitlin.api.v2.users.bookmark

import com.sorrowblue.twitlin.api.v2.TwitterAPIv2
import com.sorrowblue.twitlin.api.v2.client.Response
import com.sorrowblue.twitlin.api.v2.field.MediaField
import com.sorrowblue.twitlin.api.v2.field.PlaceField
import com.sorrowblue.twitlin.api.v2.field.PollField
import com.sorrowblue.twitlin.api.v2.field.TweetField
import com.sorrowblue.twitlin.api.v2.field.UserField
import com.sorrowblue.twitlin.api.v2.objects.PagingData
import com.sorrowblue.twitlin.api.v2.objects.Tweet
import com.sorrowblue.twitlin.api.v2.users.Expansion
import com.sorrowblue.twitlin.core.objects.TweetId
import com.sorrowblue.twitlin.core.objects.UserId

public interface BookmarkApi : TwitterAPIv2 {

    public suspend fun remove(id: UserId, tweetId: TweetId): Response<Boolean>
    public suspend fun add(id: UserId, tweetId: TweetId): Response<Boolean>
    public suspend fun get(
        id: UserId,
        maxResult: Int = 100,
        paginationToken: String? = null,
        expansions: List<Expansion>? = null,
        mediaFields: List<MediaField>? = null,
        placeFields: List<PlaceField>? = null,
        pollFields: List<PollField>? = null,
        tweetFields: List<TweetField>? = null,
        userFields: List<UserField>? = null,
    ): Response<PagingData<Tweet>>
}
