/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.androidsample

import android.app.Application
import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.authentication.AccessToken
import com.sorrowblue.twitlin.authentication.BearerToken
import kotlinx.datetime.TimeZone

internal class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Twitlin.initialize(BuildConfig.TWITTER_API_KEY, BuildConfig.TWITTER_API_SECRET) {
            accessToken =
                AccessToken(BuildConfig.TWITTER_API_ACCESS_TOKEN, BuildConfig.TWITTER_API_ACCESS_TOKEN_SECRET, "", "")
            bearerToken = BearerToken("Bearer", BuildConfig.TWITTER_API_BEARER_TOKEN)
            timeZone = TimeZone.currentSystemDefault()
        }
    }
}
