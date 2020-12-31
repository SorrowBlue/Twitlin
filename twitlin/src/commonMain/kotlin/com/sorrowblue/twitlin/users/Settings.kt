/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.users

import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.objects.TrendLocation
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * TODO
 *
 * @property alwaysUseHttps
 * @property discoverableByEmail
 * @property geoEnabled
 * @property language
 * @property protected
 * @property screenName
 * @property showAllInlineMedia
 * @property sleepTime
 * @property timeZone
 * @property trendLocation
 * @property useCookiePersonalization
 * @property allowContributorRequest
 */
@Serializable
public data class Settings(
    @SerialName("always_use_https") val alwaysUseHttps: Boolean,
    @SerialName("discoverable_by_email") val discoverableByEmail: Boolean,
    @SerialName("geo_enabled") val geoEnabled: Boolean,
    val language: String,
    val protected: Boolean,
    @SerialName("screen_name") val screenName: String,
    @SerialName("show_all_inline_media") val showAllInlineMedia: Boolean = false,
    @SerialName("sleep_time") val sleepTime: SleepTime,
    @SerialName("time_zone") val timeZone: TimeZone,
    @SerialName("trend_location") val trendLocation: List<TrendLocation>,
    @SerialName("use_cookie_personalization") val useCookiePersonalization: Boolean,
    @SerialName("allow_contributor_request") val allowContributorRequest: String
) : JvmSerializable {

    /**
     * TODO
     *
     * @property enabled
     * @property endTime
     * @property startTime
     */
    @Serializable
    public data class SleepTime(
        val enabled: Boolean,
        @SerialName("end_time") val endTime: Int? = null,
        @SerialName("start_time") val startTime: Int? = null
    ) : JvmSerializable

    /**
     * TODO
     *
     * @property name
     * @property tzinfoName
     * @property utcOffset
     */
    @Serializable
    public data class TimeZone(
        val name: String,
        @SerialName("tzinfo_name") val tzinfoName: String,
        @SerialName("utc_offset") val utcOffset: Int
    ) : JvmSerializable

}
