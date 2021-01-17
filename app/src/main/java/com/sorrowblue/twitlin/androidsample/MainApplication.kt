package com.sorrowblue.twitlin.androidsample

import android.app.Application
import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.authentication.AccessToken
import com.sorrowblue.twitlin.authentication.BearerToken
import kotlinx.datetime.TimeZone

internal class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Twitlin.initialize(BuildConfig.API_KEY, BuildConfig.API_SECRET) {
            accessToken =
                AccessToken(BuildConfig.ACCESS_TOKEN, BuildConfig.ACCESS_TOKEN_SECRET, "", "")
            bearerToken = BearerToken("Bearer", BuildConfig.BEARER_TOKEN)
            timeZone = TimeZone.currentSystemDefault()
        }
    }
}
