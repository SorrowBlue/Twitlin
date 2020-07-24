package com.sorrowblue.twitlin.objects

import com.sorrowblue.twitlin.AndroidParcel
import com.sorrowblue.twitlin.Parcelize
import com.sorrowblue.twitlin.serializers.DateTimeTzSerializer
import com.soywiz.klock.DateTimeTz
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class TwitterUser(
	val id: Long,
	@SerialName("id_str")
	val idStr: String,
	val name: String,
	@SerialName("screen_name")
	val screenName: String,
	val location: String? = null,
	val derived: Derived? = null,
	val url: String? = null,
	val entities: UserEntities,
	val description: String? = null,
	val protected: Boolean,
	val verified: Boolean,
	@SerialName("followers_count")
	val followersCount: Int,
	@SerialName("friends_count")
	val friendsCount: Int,
	@SerialName("listed_count")
	val listedCount: Int,
	@SerialName("favourites_count")
	val favouritesCount: Int,
	@SerialName("statuses_count")
	val statusesCount: Int,
	val status: TwitterTweet? = null,
	@Serializable(DateTimeTzSerializer::class)
	@SerialName("created_at")
	val createdAt: DateTimeTz,
	@SerialName("profile_banner_url")
	val profileBannerUrl: String = "",
	@SerialName("profile_image_url_https")
	val profileImageUrlHttps: String,
	@SerialName("default_profile")
	val defaultProfile: Boolean,
	@SerialName("default_profile_image")
	val defaultProfileImage: Boolean,
	@SerialName("withheld_in_countries")
	val withheldInCountries: List<String> = emptyList(),
	@SerialName("withheld_scope")
	val withheldScope: String = ""
): AndroidParcel {
	@Parcelize
	@Serializable
	data class Derived(
		val locations: List<ProfileGeo>
	): AndroidParcel

	@Parcelize
	@Serializable
	data class UserEntities(
		val url: Url? = null,
		val description: Url? = null
	) : AndroidParcel{
		@Parcelize
		@Serializable
		data class Url(
			val urls: List<Entities.URL> = emptyList()
		): AndroidParcel
	}
}
