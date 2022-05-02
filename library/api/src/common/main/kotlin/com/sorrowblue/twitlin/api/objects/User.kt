package com.sorrowblue.twitlin.api.objects

import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.annotation.JvmSerializable
import com.sorrowblue.twitlin.core.annotation.KotlinIgnoredOnParcel
import com.sorrowblue.twitlin.core.objects.UserId
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.rfc822ToLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * The User object contains Twitter User account metadata that describes the Twitter User
 * referenced. Users can author Tweets, Retweet, quote other Users Tweets, reply to Tweets, follow
 * Users, be @mentioned in Tweets and can be grouped into lists.
 *
 * @property id The integer representation of the unique identifier for this User. This number is
 * greater than 53 bits and some programming languages may have difficulty/silent defects in
 * interpreting it.
 * See [Twitter IDs](https://developer.twitter.com/en/docs/twitter-ids) for more information.
 * @property name The name of the user, as they’ve defined it. Not necessarily a person’s name.
 * Typically capped at 50 characters, but subject to change.
 * @property screenName The screen name, handle, or alias that this user identifies themselves with.
 * [screenName] are unique but subject to change. Use [id] as a user identifier whenever
 * possible. Typically a maximum of 15 characters long, but some historical accounts may exist
 * with longer names.
 * @property location Nullable. The user-defined location for this account’s profile. Not
 * necessarily a location, nor machine-parseable. This field will occasionally be fuzzily
 * interpreted by the Search service.
 * @property derived Enterprise APIs only Collection of Enrichment metadata derived for user.
 * Provides the [ProfileGeo] Enrichment metadata. See referenced documentation for more information,
 * including JSON data dictionaries.
 * @property description Nullable. The user-defined UTF-8 string describing their account.
 * @property url Nullable . A URL provided by the user in association with their profile.
 * @property entities TODO
 * @property protected When `true`, indicates that this user has chosen to protect their Tweets.
 * See [About Public and Protected Tweets](https://support.twitter.com/articles/14016-about-public-and-protected-tweets).
 * @property followersCount The number of followers this account currently has. Under certain
 * conditions of duress, this field will temporarily indicate `0`.
 * @property friendsCount The number of users this account is following (AKA their “followings”).
 * Under certain conditions of duress, this field will temporarily indicate `0`.
 * @property listedCount The number of public lists that this user is a member of.
 * @property createdAt The local datetime that the user account was created on Twitter.
 * @property favouritesCount The number of Tweets this user has liked in the account’s lifetime.
 * British spelling used in the field name for historical reasons.
 * @property verified When `true`, indicates that the user has a verified account. See
 * [Verified Accounts](https://support.twitter.com/articles/119135-faqs-about-verified-accounts).
 * @property statusesCount The number of Tweets (including retweets) issued by the user.
 * @property status TODO
 * @property profileBannerUrl The HTTPS-based URL pointing to the standard web representation of
 * the user’s uploaded profile banner. By adding a final path element of the URL, it is possible to
 * obtain different image sizes optimized for specific displays. For size variants, please see
 * [User Profile Images and Banners](https://developer.twitter.com/en/docs/accounts-and-users/user-profile-images-and-banners).
 * @property profileImageUrlHttps A HTTPS-based URL pointing to the user’s profile image.
 * @property defaultProfile When true, indicates that the user has not altered the theme or
 * background of their user profile.
 * @property defaultProfileImage When true, indicates that the user has not uploaded their own
 * profile image and a default image is used instead.
 * @property withheldInCountries When present, indicates a list of uppercase two-letter country
 * codes this content is withheld from. Twitter supports the following non-country values for this field:
 *
 * “XX” - Content is withheld in all countries “XY” - Content is withheld due to a DMCA request.
 * @property withheldScope When present, indicates that the content being withheld is a "user".
 */
@AndroidParcelize
@Serializable
public data class User(
    @SerialName("id_str")
    val id: UserId,
    val name: String? = null,
    @SerialName("screen_name")
    val screenName: String? = null,
    val location: String? = null,
    val derived: Derived? = null,
    val description: String? = null,
    val url: String? = null,
    val entities: UserEntities? = null,
    val protected: Boolean? = null,
    @SerialName("followers_count")
    val followersCount: Int? = null,
    @SerialName("friends_count")
    val friendsCount: Int? = null,
    @SerialName("listed_count")
    val listedCount: Int? = null,
    @SerialName("created_at")
    internal val _createdAt: String? = null,
    @SerialName("favourites_count")
    val favouritesCount: Int? = null,
    val verified: Boolean? = null,
    @SerialName("statuses_count")
    val statusesCount: Int? = null,
    val status: Tweet? = null,
    @SerialName("profile_banner_url")
    val profileBannerUrl: String? = null,
    @SerialName("profile_image_url_https")
    val profileImageUrlHttps: String? = null,
    @SerialName("default_profile")
    val defaultProfile: Boolean? = null,
    @SerialName("default_profile_image")
    val defaultProfileImage: Boolean? = null,
    @SerialName("withheld_in_countries")
    val withheldInCountries: List<String>? = null,
    @SerialName("withheld_scope")
    val withheldScope: String? = null,
) : AndroidParcelable, JvmSerializable {

    @KotlinIgnoredOnParcel
    @Transient
    val createdAt: LocalDateTime? = _createdAt?.rfc822ToLocalDateTime()

    /**
     * TODO
     *
     * @property locations
     */
    @AndroidParcelize
    @Serializable
    public data class Derived(
        val locations: List<ProfileGeo>
    ) : AndroidParcelable, JvmSerializable

    /**
     * TODO
     *
     * @property url
     * @property description
     */
    @AndroidParcelize
    @Serializable
    public data class UserEntities(
        val url: Url? = null,
        val description: Url
    ) : AndroidParcelable, JvmSerializable {

        /**
         * TODO
         *
         * @property urls
         */
        @AndroidParcelize
        @Serializable
        public data class Url(
            val urls: List<Entities.URL>
        ) : AndroidParcelable, JvmSerializable
    }
}
