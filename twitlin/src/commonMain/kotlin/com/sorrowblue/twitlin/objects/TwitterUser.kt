/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.objects

import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.serializers.LocalDateTimeRFC822Serializer
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class TwitterUser(
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
    @Serializable(LocalDateTimeRFC822Serializer::class)
    @SerialName("created_at")
    val createdAt: LocalDateTime,
    @SerialName("profile_banner_url")
    val profileBannerUrl: String = "",
    @SerialName("profile_image_url_https")
    val profileImageUrlHttps: String,
    @SerialName("profile_link_color")
    val profileLinkColor: String = "",
    @SerialName("default_profile")
    val defaultProfile: Boolean,
    @SerialName("default_profile_image")
    val defaultProfileImage: Boolean,
    @SerialName("withheld_in_countries")
    val withheldInCountries: List<String> = emptyList(),
    @SerialName("withheld_scope")
    val withheldScope: String = "",
) : JvmSerializable {

    @Serializable
    public data class Derived(
        val locations: List<ProfileGeo>
    ) : JvmSerializable


    @Serializable
    public data class UserEntities(
        val url: Url? = null,
        val description: Url? = null
    ) : JvmSerializable {

        @Serializable
        public data class Url(
            val urls: List<Entities.URL> = emptyList()
        ) : JvmSerializable
    }
}
