package com.sorrowblue.twitlin.accounts_users.account

import com.sorrowblue.twitlin.net.Response
import com.sorrowblue.twitlin.objects.TwitterUser

interface AccountApi {

    /**
     * Returns a representation of the requesting user if authentication was successful.
     * Returns an error message if not. Use this method to test if supplied user credentials are valid.
     *
     * @param includeEntities The entities node will not be included when set to false.
     * @param skipStatus When set to either true , t or 1 statuses will not be included in the returned user object.
     * @param includeEmail When set to true email will be returned in the user objects as a string.
     * If the user does not have an email address on their account, or if the email address is not verified, null will be returned.
     * @return Twitter user
     */
    suspend fun verifyCredentials(
        includeEntities: Boolean? = null,
        skipStatus: Boolean? = null,
        includeEmail: Boolean? = null
    ): Response<TwitterUser>

    /**
     * Removes the uploaded profile banner for the authenticating user.
     *
     * @return Returns [Response.SUCCESS] on success.
     */
    suspend fun removeProfileBanner(): Response<Unit>

    /**
     * If there are no arguments Returns settings (including current trend, geo and sleep time information) for the authenticating user.
     * Otherwise updates the authenticating user's settings.
     *
     * @param sleepTime When set to true , will enable sleep time for the user.
     * Sleep time is the time when push or SMS notifications should not be sent to the user.
     * The hour that sleep time should begin if it is enabled.
     * The hour that sleep time should end if it is enabled.
     * @param timeZone The timezone dates and times should be displayed in for the user.
     * @param trendLocationWoeid The Yahoo! Where On Earth ID to use as the user's default trend location.
     * Global information is available by using 1 as the WOEID.
     * The WOEID must be one of the locations returned by [com.sorrowblue.twitlin.trends.TrendsApi.available].
     * @param lang The language which Twitter should render in for this user.
     * The language must be specified by the appropriate two letter ISO 639-1 representation.
     * Currently supported languages are provided by [com.sorrowblue.twitlin.utilities.HelpApi.languages].
     * @return Settings (including current trend, geo and sleep time information) for the authenticating user.
     */
    suspend fun settings(
        sleepTime: TwitterSettings.SleepTime? = null,
        timeZone: String? = null,
        trendLocationWoeid: Int? = null,
        lang: String? = null
    ): Response<TwitterSettings>

    /**
     * Sets some values that users are able to set under the "Account" tab of their settings page.
     * Only the parameters specified will be updated.
     *
     * @param name Full name associated with the profile.
     * @param url URL associated with the profile. Will be prepended with http:// if not present.
     * @param location    The city or country describing where the user of the account is located.
     * The contents are not normalized or geocoded in any way.
     * @param description A description of the user owning the account.
     * @param includeEntities The entities node will not be included when set to false.
     * @param skipStatus When set to either true statuses will not be included in the returned user object.
     * @return Twitter user
     */
    suspend fun updateProfile(
        name: String? = null,
        url: String? = null,
        location: String? = null,
        description: String? = null,
        includeEntities: Boolean = true,
        skipStatus: Boolean = false
    ): Response<TwitterUser>

    /**
     * Uploads a profile banner on behalf of the authenticating user.
     * More information about sizing variations can be found in User Profile Images and Banners and [com.sorrowblue.twitlin.accounts_users.users.UsersApi.profileBanner].
     * Profile banner images are processed asynchronously.
     * The profile_banner_url and its variant sizes will not necessary be available directly after upload.
     *
     * @param banner The Base64-encoded or raw image data being uploaded as the user's new profile banner.
     * @return Returns [Response.SUCCESS] on success.
     */
    suspend fun updateProfileBanner(banner: String): Response<Unit>

    /**
     * Uploads a profile banner on behalf of the authenticating user.
     * More information about sizing variations can be found in User Profile Images and Banners and [com.sorrowblue.twitlin.accounts_users.users.UsersApi.profileBanner].
     * Profile banner images are processed asynchronously.
     * The profile_banner_url and its variant sizes will not necessary be available directly after upload.
     *
     * @param banner The Base64-encoded or raw image data being uploaded as the user's new profile banner.
     * @param width The width of the preferred section of the image being uploaded in pixels.
     * @param height The height of the preferred section of the image being uploaded in pixels.
     * @param offsetLeft The number of pixels by which to offset the uploaded image from the left.
     * @param offsetTop The number of pixels by which to offset the uploaded image from the top.
     * @return Returns [Response.SUCCESS] on success.
     */
    suspend fun updateProfileBanner(
        banner: String,
        width: Int,
        height: Int,
        offsetLeft: Int,
        offsetTop: Int
    ): Response<Unit>

    /**
     * Updates the authenticating user's profile image. Note that this method expects raw multipart data, not a URL to an image.
     * This method asynchronously processes the uploaded file before updating the user's profile image URL.
     * You can either update your local cache the next time you request the user's information
     * or at least 5 seconds after uploading the image, ask for the updated URL using [com.sorrowblue.twitlin.accounts_users.users.UsersApi.show].
     *
     * @param image The avatar image for the profile, base64-encoded.
     * Must be a valid GIF, JPG, or PNG image of less than 700 kilobytes in size.
     * Images with width larger than 400 pixels will be scaled down.
     * Animated GIFs will be converted to a static GIF of the first frame, removing the animation.
     * @param includeEntities The entities node will not be included when set to false.
     * @param skipStatus When set to either true, t or 1 statuses will not be included in the returned user objects.
     * @return Twitter user
     */
    suspend fun updateProfileImage(
        image: String,
        includeEntities: Boolean = true,
        skipStatus: Boolean = false
    ): Response<TwitterUser>
}
