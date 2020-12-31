/*
 * (c) 2020.
 */

/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.users

import com.sorrowblue.twitlin.authentication.OAuthApi
import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.objects.TwitterUser
import com.sorrowblue.twitlin.trends.TrendsApi

/**
 * With proper authorization your application can read and update a user's account and profile
 * settings. Not all settings are exposed via the API. See the API reference for details.
 */
public interface AccountApi {

    /**
     * Returns settings (including current trend, geo and sleep time information) for the
     * authenticating user.
     *
     * @return TODO
     */
    public suspend fun settings(): Response<Settings>

    /**
     * Returns an HTTP 200 OK response code and a representation of the requesting user if
     * authentication was successful; returns a 401 status code and an error message if not. Use
     * this method to test if supplied user credentials are valid.
     *
     * ### Request a User's Email Address
     *
     * The "Request email addresses from users" checkbox is available under the app permissions on
     * [developer.twitter.com](https://developer.twitter.com/en/docs/basics/developer-portal/guides/apps).
     * Privacy Policy URL and Terms of Service URL fields must be completed in the app settings in
     * order for email address access to function. If enabled, users will be informed via the
     * [OAuthApi.authorize] dialog that your app can access their email address.
     *
     * *Please note* - Your app will need to regenerate the user access tokens for previously
     * authenticated users to access their email address.
     *
     * *Please note* - You can view and edit your existing
     * [Twitter apps](https://developer.twitter.com/en/docs/basics/developer-portal/guides/apps) via
     * the [Twitter app dashboard](https://developer.twitter.com/en/apps) if you are logged into
     * your Twitter account on developer.twitter.com.
     *
     * @param includeEntities The entities node will not be included when set to false.
     * @param skipStatus When set to either true , t or 1 statuses will not be included in the
     * returned user object.
     * @param includeEmail When set to true email will be returned in the user objects as a string.
     * If the user does not have an email address on their account, or if the email address is not
     * verified, null will be returned.
     * @return TODO
     */
    public suspend fun verifyCredentials(
        includeEntities: Boolean? = null,
        skipStatus: Boolean? = null,
        includeEmail: Boolean? = null
    ): Response<TwitterUser>

    /**
     * Removes the uploaded profile banner for the authenticating user. Returns HTTP 200 upon
     * success.
     *
     * @return TODO
     */
    public suspend fun removeProfileBanner(): Response<Unit>

    /**
     * Updates the authenticating user's settings.
     *
     * @param sleepTimeEnabled When set to true, will enable sleep time for the user. Sleep time is
     * the time when push or SMS notifications should not be sent to the user.
     * @param startSleepTime The hour that sleep time should begin if it is enabled.
     * The value for this parameter should be provided in
     * [ISO 8601](http://en.wikipedia.org/wiki/ISO_8601) format (i.e. 00-23).
     * The time is considered to be in the same timezone as the user's [timeZone] setting.
     * @param endSleepTime The hour that sleep time should end if it is enabled.
     * The value for this parameter should be provided in
     * [ISO 8601](http://en.wikipedia.org/wiki/ISO_8601) format (i.e. 00-23).
     * The time is considered to be in the same timezone as the user's [timeZone] setting.
     * @param timeZone The timezone dates and times should be displayed in for the user.
     * The timezone must be one of the
     * [Rails TimeZone](http://api.rubyonrails.org/classes/ActiveSupport/TimeZone.html) names.
     * @param trendLocationWoeid The Yahoo! Where On Earth ID to use as the user's default trend
     * location. Global information is available by using 1 as the WOEID. The WOEID must be one of
     * the locations returned by [TrendsApi.available].
     * @param lang The language which Twitter should render in for this user. The language must be
     * specified by the appropriate two letter ISO 639-1 representation. Currently supported
     * languages are provided by
     * [this endpoint](https://developer.twitter.com/en/docs/developer-utilities/supported-languages/api-reference/get-help-languages).
     * @return TODO
     */
    public suspend fun settings(
        sleepTimeEnabled: Boolean? = null,
        startSleepTime: Int? = null,
        endSleepTime: Int? = null,
        timeZone: String? = null,
        trendLocationWoeid: Int? = null,
        lang: String? = null
    ): Response<Settings>

    /**
     * Sets some values that users are able to set under the "Account" tab of their settings page.
     * Only the parameters specified will be updated.
     *
     * @param name Full name associated with the profile.
     * @param url URL associated with the profile. Will be prepended with `http://` if not present.
     * @param location The city or country describing where the user of the account is located.
     * The contents are not normalized or geocoded in any way.
     * @param description A description of the user owning the account.
     * @param profileLinkColor Sets a hex value that controls the color scheme of links used on the
     * authenticating user's profile page on twitter.com. This must be a valid hexadecimal value,
     * and may be either three or six characters (ex: F00 or FF0000). This parameter replaces the
     * deprecated (and separate) update_profile_colors API method.
     * @param includeEntities The entities node will not be included when set to `false`.
     * @param skipStatus When set to either `true`, statuses will not be included in the returned
     * user object.
     * @return TODO
     */
    public suspend fun updateProfile(
        name: String? = null,
        url: String? = null,
        location: String? = null,
        description: String? = null,
        profileLinkColor: String? = null,
        includeEntities: Boolean? = null,
        skipStatus: Boolean? = null,
    ): Response<TwitterUser>

    /**
     * Uploads a profile banner on behalf of the authenticating user. More information about sizing
     * variations can be found in
     * [User Profile Images and Banners](https://developer.twitter.com/en/docs/accounts-and-users/user-profile-images-and-banners)
     * and [UsersApi.profileBanner].
     *
     * Profile banner images are processed asynchronously. The profile_banner_url and its variant
     * sizes will not necessary be available directly after upload.
     *
     * @param banner The Base64-encoded or raw image data being uploaded as the user's new profile
     * banner.
     * @param width The width of the preferred section of the image being uploaded in pixels.
     * Use with [height], [offsetLeft], and [offsetTop] to select the desired region of
     * the image to use.
     * @param height The height of the preferred section of the image being uploaded in pixels.
     * Use with [width], [offsetLeft], and [offsetTop] to select the desired region of
     * the image to use.
     * @param offsetLeft The number of pixels by which to offset the uploaded image from the left.
     * Use with [height], [width], and [offsetTop] to select the desired region of the image to use.
     * @param offsetTop The number of pixels by which to offset the uploaded image from the top. Use
     * with [height], [width], and [offsetLeft] to select the desired region of the image to use.
     * @return TODO
     */
    public suspend fun updateProfileBanner(
        banner: String,
        width: Int? = null,
        height: Int? = null,
        offsetLeft: Int? = null,
        offsetTop: Int? = null,
    ): Response<Unit>

    /**
     * Updates the authenticating user's profile image. Note that this method expects raw multipart
     * data, not a URL to an image.
     *
     * This method asynchronously processes the uploaded file before updating the user's profile
     * image URL. You can either update your local cache the next time you request the user's
     * information, or, at least 5 seconds after uploading the image, ask for the updated URL using
     * [UsersApi.show].
     *
     * @param image The avatar image for the profile, base64-encoded. Must be a valid GIF, JPG, or
     * PNG image of less than 700 kilobytes in size. Images with width larger than 400 pixels will
     * be scaled down. Animated GIFs will be converted to a static GIF of the first frame,
     * removing the animation.
     * @param includeEntities The entities node will not be included when set to false.
     * @param skipStatus When set to either true , t or 1 statuses will not be included in the
     * returned user objects.
     * @return TODO
     */
    public suspend fun updateProfileImage(
        image: String,
        includeEntities: Boolean? = null,
        skipStatus: Boolean? = null
    ): Response<TwitterUser>

}
