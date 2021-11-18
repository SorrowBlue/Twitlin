package com.sorrowblue.twitlin.v2.client

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.v2.objects.Media
import com.sorrowblue.twitlin.v2.objects.Place
import com.sorrowblue.twitlin.v2.objects.Poll
import com.sorrowblue.twitlin.v2.objects.Tweet
import com.sorrowblue.twitlin.v2.objects.User
import kotlinx.serialization.Serializable

/**
 * TODO
 *
 * @property tweets
 * @property users
 * @property places
 * @property media
 * @property polls
 */
@AndroidParcelize
@Serializable
public data class Includes(
    val tweets: List<Tweet> = emptyList(),
    val users: List<User> = emptyList(),
    val places: List<Place> = emptyList(),
    val media: List<Media> = emptyList(),
    val polls: List<Poll> = emptyList(),
) : AndroidParcelable, JvmSerializable
