package com.sorrowblue.twitlin.accounts_users.account

import com.sorrowblue.twitlin.objects.TrendLocation
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TwitterSettings(
    @SerialName("always_use_https")
    val alwaysUseHttps: Boolean,
    @SerialName("discoverable_by_email")
    val discoverableByEmail: Boolean,
    @SerialName("geo_enabled")
    val geoEnabled: Boolean,
    val language: String,
    val protected: Boolean,
    @SerialName("screen_name")
    val screenName: String,
    @SerialName("show_all_inline_media")
    val showAllInlineMedia: Boolean = false,
    @SerialName("sleep_time")
    val sleepTime: SleepTime,
    @SerialName("time_zone")
    val timeZone: TimeZone,
    @SerialName("trend_location")
    val trendLocation: List<TrendLocation>,
    @SerialName("use_cookie_personalization")
    val useCookiePersonalization: Boolean,
    @SerialName("allow_contributor_request")
    val allowContributorRequest: String
) {
    @Serializable
    data class SleepTime(
        val enabled: Boolean,
        @SerialName("end_time")
        val endTime: Int? = null,
        @SerialName("start_time")
        val startTime: Int? = null
    )

    @Serializable
    data class TimeZone(
        val name: String,
        @SerialName("tzinfo_name")
        val tzinfoName: String,
        @SerialName("utc_offset")
        val utcOffset: Int
    )

}
