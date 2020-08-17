package com.sorrowblue.twitlin.v2.objects

import com.sorrowblue.twitlin.Parcelable
import com.sorrowblue.twitlin.Parcelize
import com.sorrowblue.twitlin.v2.serializer.DateTimeTzSerializer
import com.soywiz.klock.DateTimeTz
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class User(
	val id: String,
	val name: String,
	val username: String,
	@SerialName("created_at")
	@Serializable(DateTimeTzSerializer::class)
	val createdAt: DateTimeTz? = null,
	val description: String? = null,
	val entities: Entities? = null,
	val location: String? = null,
	@SerialName("pinned_tweet_id")
	val pinnedTweetId: String? = null,
	@SerialName("profile_image_url")
	val profileImageUrl: String? = null,
	val protected: Boolean? = null,
	@SerialName("public_metrics")
	val publicMetrics: PublicMetrics? = null,
	val url: String? = null,
	val verified: Boolean? = null,
	val withheld: Withheld? = null,
) : Parcelable
